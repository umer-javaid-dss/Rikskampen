package com.kampen.riks.app.rikskampen.data_manager;

import android.content.Context;

import com.kampen.riks.app.rikskampen.MyApplication;
import com.kampen.riks.app.rikskampen.api.remote_api.APIService;
import com.kampen.riks.app.rikskampen.api.remote_api.API_Provider;
import com.kampen.riks.app.rikskampen.api.remote_api.Generic_Result;
import com.kampen.riks.app.rikskampen.api.remote_api.models.RemoteUser;
import com.kampen.riks.app.rikskampen.api.remote_api.models.RemoteUserResult;
import com.kampen.riks.app.rikskampen.utils.SaveSharedPreference;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Data_manager {



    Manage_Login    mManage_Login;
    Manage_Token    mManage_Token;
    Manage_SignUp    mManage_SignUp;




    public    void  loginUserAPI(String email, String password,final Context context)
    {


        final APIService service= API_Provider.provideApi();

        HashMap<String,String> hm=new HashMap();

        hm.put("email",email);
        hm.put("password",password);


        Call<Generic_Result> call = service.userLogin(hm);


        call.enqueue(new Callback<Generic_Result>() {
            @Override
            public void onResponse(Call<Generic_Result> call, Response<Generic_Result> response) {

                Generic_Result  obj=null;

                obj=response.body();

                if(response.message()!=null &&  response.message().contentEquals("Unauthorized"))
                {

                    if(mManage_Login!=null) { //mManage_Login.onTokenFailed(response.message());
                    }
                }
                else if(mManage_Login!=null) {
                   // SaveSharedPreference.saveUserToken(context,obj.getAccess_token());
                    //mManage_Login.onTokenSuccess("bearer "+obj.getAccess_token());
                }

            }

            @Override
            public void onFailure(Call<Generic_Result> call, Throwable t) {

                t.toString();

                if(mManage_Login!=null) {
                   // mManage_Login.onTokenFailed(t.getMessage());
                }

            }
        });

    }



    public    void  getUserAPI(String email, String password)
    {


        try {

            APIService service = API_Provider.provideApi();

            HashMap<String,String> hm=new HashMap();
            hm.put("email",email);
            hm.put("password",password);

            Call<Generic_Result<RemoteUserResult>> call = service.getUser(hm);


            call.enqueue(new Callback<Generic_Result<RemoteUserResult>>() {
                @Override
                public void onResponse(Call<Generic_Result<RemoteUserResult>> call, Response<Generic_Result<RemoteUserResult>> response) {

                    Generic_Result obj = null;

                    obj = response.body();


                    if(mManage_Login!=null) {
                        mManage_Login.onLoginSuccess(obj);
                    }



                }

                @Override
                public void onFailure(Call<Generic_Result<RemoteUserResult>> call, Throwable t) {
                    //java.lang.IllegalStateException: Expected BEGIN_OBJECT but was STRING at line 1 column 1 path $
                    t.toString();

                    if (mManage_Login != null) {
                        try {
                            mManage_Login.onLoginFailed(t.getMessage());
                        }catch (Exception ex){}
                    }

                }
            });

        }catch (Exception ex)
        {
            ex.toString();
        }

    }



    public    void  signUpUserAPI(RemoteUser obj)
    {


        try {

            APIService service = API_Provider.provideApi();


            HashMap<String,String> hm=new HashMap();




            hm.put("email",obj.getEmail());
            hm.put("password",obj.getPassword());
            hm.put("firstname",obj.getFirstname());
            hm.put("lastname", obj.getLastname());


            Call<Generic_Result<RemoteUserResult>> call = service.userSignUp(hm);


            call.enqueue(new Callback<Generic_Result<RemoteUserResult>>() {
                @Override
                public void onResponse(Call<Generic_Result<RemoteUserResult>> call, Response<Generic_Result<RemoteUserResult>> response) {

                    Generic_Result<RemoteUserResult> obj = null;

                    obj = response.body();


                    if(mManage_SignUp!=null) {
                        mManage_SignUp.onSignUpSuccess( obj);
                    }



                }

                @Override
                public void onFailure(Call<Generic_Result<RemoteUserResult>> call, Throwable t) {
                    //java.lang.IllegalStateException: Expected BEGIN_OBJECT but was STRING at line 1 column 1 path $
                    t.toString();

                    if (mManage_SignUp != null) {
                        try {
                            mManage_SignUp.onSignUpFailed(t.getMessage());
                        }catch (Exception ex){}
                    }

                }
            });

        }catch (Exception ex)
        {
            ex.toString();
        }

    }


    public  void  setTokenListener(Manage_Token listener)
    {
        this.mManage_Token=listener;
    }
    public  void  setSignUpListener(Manage_SignUp listener)
    {
        this.mManage_SignUp=listener;
    }
    public  void  setLoginListener(Manage_Login listener)
    {
        this.mManage_Login=listener;
    }



    public  interface  Manage_Token
    {

        public void  onTokenSuccess(String token);
        public void  onTokenFailed(String error);

    }



    public  interface  Manage_Login
    {


           public void  onLoginSuccess(Generic_Result<RemoteUserResult> obj);
           public void onLoginFailed(String message);
    }


    public  interface  Manage_SignUp
    {


        public void  onSignUpSuccess(Generic_Result<RemoteUserResult> res);
        public void onSignUpFailed(String message);
    }





}
