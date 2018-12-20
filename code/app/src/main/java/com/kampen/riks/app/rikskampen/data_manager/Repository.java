package com.kampen.riks.app.rikskampen.data_manager;

import com.facebook.stetho.server.http.HttpStatus;
import com.kampen.riks.app.rikskampen.DI.LocalApiService;
import com.kampen.riks.app.rikskampen.DI.RemoteApiService;
import com.kampen.riks.app.rikskampen.DI.ServiceLocator;
import com.kampen.riks.app.rikskampen.api.remote_api.APIService;
import com.kampen.riks.app.rikskampen.api.remote_api.Generic_Result;
import com.kampen.riks.app.rikskampen.api.remote_api.ResponseStatus;
import com.kampen.riks.app.rikskampen.api.remote_api.models.RemoteUserResult;
import com.kampen.riks.app.rikskampen.api.remote_api.models.RemoteUserResultDB;
import com.kampen.riks.app.rikskampen.user.model.DB_User;

import java.util.HashMap;

import io.realm.Realm;
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






    public  static    void   getUser(String email,String password,final ResponseStatus responseStatus)
    {
         APIService mWebService;
         Realm      mLocalService;

        mWebService  = ((RemoteApiService) ServiceLocator.getService(RemoteApiService.NAME)).getApiService();
        mLocalService= ((LocalApiService)  ServiceLocator.getService(LocalApiService.NAME)).getmRealm();



        HashMap<String,String> hm=new HashMap();
        hm.put(EMAIL,email);
        hm.put(PASSWORD,password);



        Call<Generic_Result<RemoteUserResultDB>> call = mWebService.getUserDB(hm);


        call.enqueue(new Callback<Generic_Result<RemoteUserResultDB>>() {
            @Override
            public void onResponse(Call<Generic_Result<RemoteUserResultDB>> call, Response<Generic_Result<RemoteUserResultDB>> response) {

                  Generic_Result<RemoteUserResultDB> obj = null;

                 obj = response.body();

                 if(obj.getCode()== HttpStatus.HTTP_OK  && obj.getStatus().equals(STATUS_SUCCESS)) {

                     DB_User.addUserToDb(mLocalService, obj.getResult().getUser());
                 }

                 responseStatus.setCode(obj.getCode());
                 responseStatus.setMsg(obj.getMsg());
                 responseStatus.setStatus(obj.getStatus());

                responseStatus.onResult(responseStatus);


            }

            @Override
            public void onFailure(Call<Generic_Result<RemoteUserResultDB>> call, Throwable t) {

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


        Call<Generic_Result<RemoteUserResultDB>> call = mWebService.userSignUpDB(hm);

        call.enqueue(new Callback<Generic_Result<RemoteUserResultDB>>() {
            @Override
            public void onResponse(Call<Generic_Result<RemoteUserResultDB>> call, Response<Generic_Result<RemoteUserResultDB>> response) {

                Generic_Result<RemoteUserResultDB> obj = null;

                obj = response.body();

                if(obj.getCode()== HttpStatus.HTTP_OK  && obj.getStatus().equals(STATUS_SUCCESS)) {

                    DB_User.addUserToDb(mLocalService, obj.getResult().getUser());
                }

                responseStatus.setCode(obj.getCode());
                responseStatus.setMsg(obj.getMsg());
                responseStatus.setStatus(obj.getStatus());

                responseStatus.onResult(responseStatus);


            }

            @Override
            public void onFailure(Call<Generic_Result<RemoteUserResultDB>> call, Throwable t) {

                t.toString();



                responseStatus.setCode(API_HIT_FAILED);
                responseStatus.setMsg(t.getMessage());
                responseStatus.setStatus(STATUS_FAILED);

                responseStatus.onResult(responseStatus);


            }
        });



    }



}
