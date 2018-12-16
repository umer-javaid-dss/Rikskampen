package com.kampen.riks.app.rikskampen.leader.activity.fragments.home.addactivity.model;

public class DailyPick {

    private  String  id;

    private   String  user_id;

    private   String  picPath;

    private   byte    []  picData;

    private    String  takenTimeDate;

    private    String   lat;

    private    String  lan;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPicPath() {
        return picPath;
    }

    public void setPicPath(String picPath) {
        this.picPath = picPath;
    }

    public byte[] getPicData() {
        return picData;
    }

    public void setPicData(byte[] picData) {
        this.picData = picData;
    }

    public String getTakenTimeDate() {
        return takenTimeDate;
    }

    public void setTakenTimeDate(String takenTimeDate) {
        this.takenTimeDate = takenTimeDate;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLan() {
        return lan;
    }

    public void setLan(String lan) {
        this.lan = lan;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
}
