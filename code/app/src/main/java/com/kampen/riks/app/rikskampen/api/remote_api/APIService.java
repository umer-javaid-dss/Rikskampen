package com.kampen.riks.app.rikskampen.api.remote_api;

import com.kampen.riks.app.rikskampen.api.remote_api.models.RemoteUser;
import com.kampen.riks.app.rikskampen.api.remote_api.models.RemoteUserResult;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface APIService {



    /*@FormUrlEncoded
    @POST("/SlimApp/p_ublic/signup_driver.php")
    Call<Generic_Result> registerDriver(
            @FieldMap HashMap<String, String> driver

    );




    @Multipart
    @POST("/SlimApp/p_ublic/signup_driver.php")
    Call<Generic_Result> registerDriverTest(


            @PartMap() Map<String, RequestBody> partMap, @Part MultipartBody.Part file

    );


    @Multipart
    @POST("/SlimApp/p_ublic/updateDriverInfo.php")
    Call<Generic_Result> updateDriverInfo(
            @PartMap() Map<String, RequestBody> partMap, @Part MultipartBody.Part file
    );


    @Multipart
    @POST("/SlimApp/p_ublic/updateRiderInfo.php")
    Call<Generic_Result> updateRiderInfo(
            @PartMap() Map<String, RequestBody> partMap, @Part MultipartBody.Part file
    );


    @FormUrlEncoded
    @POST("/SlimApp/p_ublic/signup_rider.php")
    Call<Generic_Result> registerRider(
            @FieldMap HashMap<String, String> rider

    );

    @FormUrlEncoded
    @POST("/SlimApp/p_ublic/login_driver.php")
    Call<Generic_Result<D_Driver>> loginDriver(
            @FieldMap HashMap<String, String> driver

    );


    @FormUrlEncoded
    @POST("/SlimApp/p_ublic/isDriverAlreadyExist.php")
    Call<Generic_Result<Object>> isDriverAlreadyExist(
            @FieldMap HashMap<String, String> driver

    );


    @FormUrlEncoded
    @POST("/SlimApp/p_ublic/login_rider.php")
    Call<Generic_Result<R_Rider>> loginRider(
            @FieldMap HashMap<String, String> rider

    );

    @FormUrlEncoded
    @POST("/SlimApp/p_ublic/isRiderAlreadyExist.php")
    Call<Generic_Result<R_Rider>> isRiderAlreadyExist(
            @FieldMap HashMap<String, String> rider

    );
*/



    @FormUrlEncoded
    @POST("public/index.php/api/login")
    Call<Generic_Result> userLogin(
            @FieldMap HashMap<String, String> loginHM

    );



    @FormUrlEncoded
    @POST("public/index.php/api/login")
    Call<Generic_Result<RemoteUserResult>> getUser( @FieldMap HashMap<String, String> loginHM);


    @FormUrlEncoded
    @POST("public/index.php/api/userSingUp")
    Call<Generic_Result<RemoteUserResult>> userSignUp(

            @FieldMap HashMap<String, String> signUpHM

    );



    @FormUrlEncoded
    @POST("public/index.php/api/auth/userUpdate")
    Call<Generic_Result<RemoteUserResult>> userUpdate(

            @FieldMap HashMap<String, String> signUpHM

    );







}
