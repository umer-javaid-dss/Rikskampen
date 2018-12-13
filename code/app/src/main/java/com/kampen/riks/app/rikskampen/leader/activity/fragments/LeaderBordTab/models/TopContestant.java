package com.kampen.riks.app.rikskampen.leader.activity.fragments.LeaderBordTab.models;

public class TopContestant {


    private  String  id;
    private  String  steps;
    private  String  name;
    private  String  profileImage;
    private  int  position;
    public   int tempResId;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSteps() {
        return steps;
    }

    public void setSteps(String steps) {
        this.steps = steps;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
