package com.example.api.ramsha.myapplication.gridview;

public class AndroidCenterDataModel {
    int id;
    String headline;

    public AndroidCenterDataModel(int id, String headline) {
        this.id = id;
        this.headline = headline;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }
}
