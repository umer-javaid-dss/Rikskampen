package com.kampen.riks.app.rikskampen.api.remote_api.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.kampen.riks.app.rikskampen.user.model.DB_User;

public class RemoteUserResultDB {

    @SerializedName("token")
    @Expose
    private  String   token;



    @SerializedName("user")
    @Expose
    private DB_User user;


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public DB_User getUser() {
        return user;
    }

    public void setUser(DB_User user) {
        this.user = user;
    }




}
