package com.kampen.riks.app.rikskampen.user.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.Realm;
import io.realm.RealmObject;

public class DB_User extends RealmObject {



    private  String  id;


    private  String  f_name;


    private  String  l_name;


    private  String  _email;


    private  String  _pass;


    private  String  profile_image;


    private  String role;


    private  int  age;
    private  String  dob;
    private  int  height_in_feet;
    private  int  height_in_inches;
    private  String  height_unit;
    private   int     weight;

    private  String  weight_unit;
    private   int    user_gender;

    private   byte []  profilePicData;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getF_name() {
        return f_name;
    }

    public void setF_name(String f_name) {
        this.f_name = f_name;
    }

    public String getL_name() {
        return l_name;
    }

    public void setL_name(String l_name) {
        this.l_name = l_name;
    }



    public String getProfile_image() {
        return profile_image;
    }

    public void setProfile_image(String profile_image) {
        this.profile_image = profile_image;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getHeight_in_feet() {
        return height_in_feet;
    }

    public void setHeight_in_cm(int height_in_feet) {
        this.height_in_feet = height_in_feet;
    }

    public int getHeight_in_inches() {
        return height_in_inches;
    }

    public void setHeight_in_inches(int height_in_inches) {
        this.height_in_inches = height_in_inches;
    }

    public String getHeight_unit() {
        return height_unit;
    }

    public void setHeight_unit(String height_unit) {
        this.height_unit = height_unit;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getWeight_unit() {
        return weight_unit;
    }

    public void setWeight_unit(String weight_unit) {
        this.weight_unit = weight_unit;
    }

    public int getUser_gender() {
        return user_gender;
    }

    public void setUser_gender(int user_gender) {
        this.user_gender = user_gender;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public byte[] getProfilePicData() {
        return profilePicData;
    }

    public void setProfilePicData(byte[] profilePicData) {
        this.profilePicData = profilePicData;
    }


    public String getEmail() {
        return _email;
    }

    public void setEmail(String _email) {
        this._email = _email;
    }

    public String getPass() {
        return _pass;
    }

    public void setPass(String _pass) {
        this._pass = _pass;
    }
}
