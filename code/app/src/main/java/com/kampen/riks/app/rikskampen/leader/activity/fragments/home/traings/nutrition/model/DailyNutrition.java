package com.kampen.riks.app.rikskampen.leader.activity.fragments.home.traings.nutrition.model;

import io.realm.RealmObject;

public class DailyNutrition extends RealmObject {


    private String   id;

    private String   path;

    private String    thumbNailPath;

    private int    thumbNailPathTemp;

    private String    title;

    private String     shortDes;


    private String    dateTime;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getThumbNailPath() {
        return thumbNailPath;
    }

    public void setThumbNailPath(String thumbNailPath) {
        this.thumbNailPath = thumbNailPath;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShortDes() {
        return shortDes;
    }

    public void setShortDes(String shortDes) {
        this.shortDes = shortDes;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public int getThumbNailPathTemp() {
        return thumbNailPathTemp;
    }

    public void setThumbNailPathTemp(int thumbNailPathTemp) {
        this.thumbNailPathTemp = thumbNailPathTemp;
    }
}
