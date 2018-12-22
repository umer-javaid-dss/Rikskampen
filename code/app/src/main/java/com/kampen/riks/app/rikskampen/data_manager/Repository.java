package com.kampen.riks.app.rikskampen.data_manager;

import com.facebook.stetho.server.http.HttpStatus;
import com.kampen.riks.app.rikskampen.DI.LocalApiService;
import com.kampen.riks.app.rikskampen.DI.RemoteApiService;
import com.kampen.riks.app.rikskampen.DI.ServiceLocator;
import com.kampen.riks.app.rikskampen.api.remote_api.APIService;
import com.kampen.riks.app.rikskampen.api.remote_api.Generic_Result;
import com.kampen.riks.app.rikskampen.api.remote_api.ResponseStatus;
import com.kampen.riks.app.rikskampen.api.remote_api.models.RemoteUser;
import com.kampen.riks.app.rikskampen.api.remote_api.models.RemoteUserResult;
import com.kampen.riks.app.rikskampen.user.model.DB_User;
import com.kampen.riks.app.rikskampen.utils.Constants;


import java.util.HashMap;

import io.realm.Realm;
import io.realm.RealmResults;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Repository {



    public  static  final String EMAIL ="email";

    public  static  final String PASSWORD ="password";

    public  static  final String F_NAME ="firstname";

    public  static  final String L_NAME ="lastname";

    public  static  final String STATUS_SUCCESS ="sucess";

    public  static  final String STATUS_FAILED="failed";

    public  static  final int  API_HIT_FAILED =102;



    public static   boolean addUserLocal(Realm realm,RemoteUser userJson)
    {
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {

                DB_User db_user = realm.createObject(DB_User.class);

                db_user.setF_name(userJson.getFirstname());
                db_user.setL_name(userJson.getLastname());
                db_user.setEmail(userJson.getEmail());
                db_user.setPass("12345");

            }
        });

        return  false;
    }

    
    public   static   DB_User  provideUserLocal( String email, String pass)
    {


        Realm mLocalService= ((LocalApiService)  ServiceLocator.getService(LocalApiService.NAME)).getmRealm();


        mLocalService.refresh();

        RealmResults<DB_User> userList=mLocalService.where(DB_User.class)
                .equalTo(Constants.USER_EMAIL_TAG,email)
                .equalTo(Constants.USER_PASS_TAG,pass)
                .findAll();


        DB_User user = null;
                
        if(userList!=null && userList.size()>0) {

            user = userList.get(0);
        }
        

        return  user;
        
    }




    public static   void updateUserLocal(final DB_User  user)
    {

        Realm mLocalService= ((LocalApiService)  ServiceLocator.getService(LocalApiService.NAME)).getmRealm();

        mLocalService.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {

                DB_User db_user = user;

                realm.insertOrUpdate(db_user);

            }
        });
    }


    public static   void updateUserLocalWithRemoteUser(final RemoteUser  rUser,final DB_User  user)
    {

        Realm mLocalService= ((LocalApiService)  ServiceLocator.getService(LocalApiService.NAME)).getmRealm();

        mLocalService.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {

                DB_User db_user = user;

                db_user.setF_name(rUser.getFirstname());
                db_user.setL_name(rUser.getLastname());
                db_user.setEmail(rUser.getEmail());
                db_user.setPass(rUser.getPassword());

                realm.insertOrUpdate(db_user);

            }
        });
    }


    public  static    void   getUser(String email,String password,final ResponseStatus responseStatus)
    {
         APIService mWebService;
         Realm      mLocalService;

        mWebService  = ((RemoteApiService) ServiceLocator.getService(RemoteApiService.NAME)).getApiService();
        mLocalService= ((LocalApiService)  ServiceLocator.getService(LocalApiService.NAME)).getmRealm();



        HashMap<String,String> hm=new HashMap();
        hm.put(EMAIL,email);
        hm.put(PASSWORD,password);



        Call<Generic_Result<RemoteUserResult>> call = mWebService.getUser(hm);


        call.enqueue(new Callback<Generic_Result<RemoteUserResult>>() {
            @Override
            public void onResponse(Call<Generic_Result<RemoteUserResult>> call, Response<Generic_Result<RemoteUserResult>> response) {

                  Generic_Result<RemoteUserResult> obj = null;

                 obj = response.body();

                 if(obj.getCode()== HttpStatus.HTTP_OK  && obj.getStatus().equals(STATUS_SUCCESS)) {

                     addUserLocal(mLocalService, obj.getResult().getUser());
                 }

                 responseStatus.setCode(obj.getCode());
                 responseStatus.setMsg(obj.getMsg());
                 responseStatus.setStatus(obj.getStatus());

                responseStatus.onResult(responseStatus);


            }

            @Override
            public void onFailure(Call<Generic_Result<RemoteUserResult>> call, Throwable t) {

                t.toString();



                responseStatus.setCode(API_HIT_FAILED);
                responseStatus.setMsg(t.getMessage());
                responseStatus.setStatus(STATUS_FAILED);

                responseStatus.onResult(responseStatus);


            }
        });



    }


    public  static    void   signUpUser(String fname,String lname,String password,String email,final ResponseStatus responseStatus)
    {
        APIService mWebService;
        Realm      mLocalService;

        mWebService  = ((RemoteApiService) ServiceLocator.getService(RemoteApiService.NAME)).getApiService();
        mLocalService= ((LocalApiService)  ServiceLocator.getService(LocalApiService.NAME)).getmRealm();

        HashMap<String,String> hm=new HashMap();
        hm.put(L_NAME,lname);
        hm.put(F_NAME,fname);
        hm.put(EMAIL,email);
        hm.put(PASSWORD,password);


        Call<Generic_Result<RemoteUserResult>> call = mWebService.userSignUp(hm);

        call.enqueue(new Callback<Generic_Result<RemoteUserResult>>() {
            @Override
            public void onResponse(Call<Generic_Result<RemoteUserResult>> call, Response<Generic_Result<RemoteUserResult>> response) {

                Generic_Result<RemoteUserResult> obj = null;

                obj = response.body();

                if(obj.getCode()== HttpStatus.HTTP_OK  && obj.getStatus().equals(STATUS_SUCCESS)) {

                     addUserLocal(mLocalService, obj.getResult().getUser());
                }

                responseStatus.setCode(obj.getCode());
                responseStatus.setMsg(obj.getMsg());
                responseStatus.setStatus(obj.getStatus());

                responseStatus.onResult(responseStatus);


            }

            @Override
            public void onFailure(Call<Generic_Result<RemoteUserResult>> call, Throwable t) {

                t.toString();



                responseStatus.setCode(API_HIT_FAILED);
                responseStatus.setMsg(t.getMessage());
                responseStatus.setStatus(STATUS_FAILED);

                responseStatus.onResult(responseStatus);


            }
        });



    }



    public  static    void   editUser(String fname,String lname,String password,String email,final ResponseStatus responseStatus)
    {
        APIService mWebService;
        Realm      mLocalService;

        mWebService  = ((RemoteApiService) ServiceLocator.getService(RemoteApiService.NAME)).getApiService();
        mLocalService= ((LocalApiService)  ServiceLocator.getService(LocalApiService.NAME)).getmRealm();

        HashMap<String,String> hm=new HashMap();
        hm.put(L_NAME,lname);
        hm.put(F_NAME,fname);
        hm.put(EMAIL,email);
        hm.put(PASSWORD,password);


        Call<Generic_Result<String>> call = mWebService.userUpdate(hm);

        call.enqueue(new Callback<Generic_Result<String>>() {
            @Override
            public void onResponse(Call<Generic_Result<String>> call, Response<Generic_Result<String>> response) {

                Generic_Result<String> obj = null;

                obj = response.body();


                responseStatus.setCode(obj.getCode());
                responseStatus.setMsg(obj.getMsg());
                responseStatus.setStatus(obj.getStatus());

                responseStatus.onResult(responseStatus);


            }

            @Override
            public void onFailure(Call<Generic_Result<String>> call, Throwable t) {

                t.toString();



                responseStatus.setCode(API_HIT_FAILED);
                responseStatus.setMsg(t.getMessage());
                responseStatus.setStatus(STATUS_FAILED);

                responseStatus.onResult(responseStatus);


            }
        });



    }


}
