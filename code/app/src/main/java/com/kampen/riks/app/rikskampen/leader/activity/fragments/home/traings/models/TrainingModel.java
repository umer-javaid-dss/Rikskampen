package com.kampen.riks.app.rikskampen.leader.activity.fragments.home.traings.models;

public class TrainingModel {

    private String id;

    private String thumNail;
    private String mJoinedPersons;
    private String week;
    private String  title;
    private String  des;
    private int  temPic;
    private String  joinedPeople;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getThumNail() {
        return thumNail;
    }

    public void setThumNail(String thumNail) {
        this.thumNail = thumNail;
    }

    public String getmJoinedPersons() {
        return mJoinedPersons;
    }

    public void setmJoinedPersons(String mJoinedPersons) {
        this.mJoinedPersons = mJoinedPersons;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public int getTemPic() {
        return temPic;
    }

    public void setTemPic(int temPic) {
        this.temPic = temPic;
    }

    public String getJoinedPeople() {
        return joinedPeople;
    }

    public void setJoinedPeople(String joinedPeople) {
        this.joinedPeople = joinedPeople;
    }
}
