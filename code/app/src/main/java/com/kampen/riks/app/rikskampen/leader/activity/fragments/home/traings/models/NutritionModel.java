package com.kampen.riks.app.rikskampen.leader.activity.fragments.home.traings.models;

import io.realm.Realm;
import io.realm.RealmObject;


public class NutritionModel extends RealmObject {

    private String mHabitId;
    private String mHabitName;
    private String mHabitImgUrl;
    private String mJoinedPersons;
    private String mWorkingFrom;
    private int    tempPicture;



    public String getHabitId() {
        return mHabitId;
    }

    public void setHabitId(String mHabitId) {
        this.mHabitId = mHabitId;
    }

    public String getHabitName() {
        return mHabitName;
    }

    public void setHabitName(String mHabitName) {
        this.mHabitName = mHabitName;
    }

    public String getHabitImgUrl() {
        return mHabitImgUrl;
    }

    public void setHabitImgUrl(String mHabitImgUrl) {
        this.mHabitImgUrl = mHabitImgUrl;
    }

    public String getJoinedPersons() {
        return mJoinedPersons;
    }

    public void setJoinedPersons(String mJoinedPersons) {
        this.mJoinedPersons = mJoinedPersons;
    }

    public String getWorkingFrom() {
        return mWorkingFrom;
    }

    public void setWorkingFrom(String mWorkingFrom) {
        this.mWorkingFrom = mWorkingFrom;
    }

    public int getTempPicture() {
        return tempPicture;
    }

    public void setTempPicture(int tempPicture) {
        this.tempPicture = tempPicture;
    }
}
