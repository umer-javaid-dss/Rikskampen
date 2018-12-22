package com.kampen.riks.app.rikskampen.api.remote_api;

import com.kampen.riks.app.rikskampen.DI.Service;
import com.kampen.riks.app.rikskampen.api.remote_api.models.RemoteUser;
import com.kampen.riks.app.rikskampen.api.remote_api.models.RemoteUserResult;
import com.kampen.riks.app.rikskampen.api.remote_api.models.RemoteUserResultDB;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface APIService  {






    @FormUrlEncoded
    @POST("public/index.php/api/login")
    Call<Generic_Result> userLogin(
            @FieldMap HashMap<String, String> loginHM

    );



    @FormUrlEncoded
    @POST("public/index.php/api/login")
    Call<Generic_Result<RemoteUserResult>> getUser( @FieldMap HashMap<String, String> loginHM);


    @FormUrlEncoded
    @POST("public/index.php/api/login")
    Call<Generic_Result<RemoteUserResultDB>> getUserDB(@FieldMap HashMap<String, String> loginHM);


    @FormUrlEncoded
    @POST("public/index.php/api/userSingUp")
    Call<Generic_Result<RemoteUserResult>> userSignUp(

            @FieldMap HashMap<String, String> signUpHM

    );



    @FormUrlEncoded
    @POST("public/index.php/api/userSingUp")
    Call<Generic_Result<RemoteUserResultDB>> userSignUpDB(

            @FieldMap HashMap<String, String> signUpHM

    );


    @FormUrlEncoded
    @POST("public/index.php/api/auth/userUpdate")
    Call<Generic_Result<String>> userUpdate(

            @FieldMap HashMap<String, String> editProfileHM

    );







}
