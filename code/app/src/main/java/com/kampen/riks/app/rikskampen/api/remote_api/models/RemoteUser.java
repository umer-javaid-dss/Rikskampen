package com.kampen.riks.app.rikskampen.api.remote_api.models;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RemoteUser {


    @SerializedName("id")
    @Expose
    private  String   id;

    @SerializedName("password")
    @Expose
    private  String   password;

    @SerializedName("firstname")
    @Expose
    private  String   firstname;
    @SerializedName("lastname")
    @Expose
    private  String   lastname;
    @SerializedName("email")
    @Expose
    private  String   email;
    @SerializedName("email_verified_at")
    @Expose
    private  String email_verified_at;
    @SerializedName("role_id")
    @Expose
    private  String      role;
    @SerializedName("created_at")
    @Expose
    private  String      created_at;
    @SerializedName("updated_at")
    @Expose
    private  String      updated_at;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail_verified_at() {
        return email_verified_at;
    }

    public void setEmail_verified_at(String email_verified_at) {
        this.email_verified_at = email_verified_at;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
