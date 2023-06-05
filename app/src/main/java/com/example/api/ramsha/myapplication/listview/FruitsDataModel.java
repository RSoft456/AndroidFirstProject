package com.example.api.ramsha.myapplication.listview;

public class FruitsDataModel {
    String name;
    String description;
    int Imageid;

    public FruitsDataModel(String name, String description, int id) {
        this.name = name;
        this.description = description;
        this.Imageid = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getImageid() {
        return Imageid;
    }

    public void setId(int id) {
        this.Imageid = id;
    }
}
