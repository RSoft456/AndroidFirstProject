package com.example.api.ramsha.myapplication.gridview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;



import android.os.Bundle;

import com.example.api.ramsha.myapplication.R;
import com.example.api.ramsha.myapplication.listview.FruitsDataModel;

import java.util.ArrayList;

public class GridView extends AppCompatActivity {
    Toolbar toolbar;
    android.widget.GridView gridView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_view);
        toolbar= findViewById(R.id.toolbar);
        gridView=findViewById(R.id.gridView);
        toolbar.setNavigationIcon(R.drawable.baseline_menu_24);
        ArrayList<AndroidCenterDataModel> androidList = new ArrayList<AndroidCenterDataModel>();
        androidList.add(new AndroidCenterDataModel(R.drawable.pic1,"Lync 2013 gets updated for android with new anonymous join features and is very flexible."));
        androidList.add(new AndroidCenterDataModel(R.drawable.pic2,"Vodafone U.K. starts Android Lollipop update for LG G3"));
        androidList.add(new AndroidCenterDataModel(R.drawable.pic3,"Nexus 5: Past, Present and future"));
        androidList.add(new AndroidCenterDataModel(R.drawable.pic4,"Nexus 9 with LTE now on sale in the google playe store"));
        androidList.add(new AndroidCenterDataModel(R.drawable.pic1,"blackberry updates its BBM app with support for Android 5.0 lollipop and it is amazing."));
        androidList.add(new AndroidCenterDataModel(R.drawable.pic2,"Motorola Droid Turbo pr Moto X (2014): Which should you buy?"));
        androidList.add(new AndroidCenterDataModel(R.drawable.pic3,"Google Play Newsstand expands its reach to Mexico and three more countries."));
        androidList.add(new AndroidCenterDataModel(R.drawable.pic4,"Googke maps add provides lane guidance to its users in European countries."));
        androidList.add(new AndroidCenterDataModel(R.drawable.pic1,"Nexus 5: Past, Present and future"));
        androidList.add(new AndroidCenterDataModel(R.drawable.pic2,"Nexus 9 with LTE now on sale in the google playe store"));
        androidList.add(new AndroidCenterDataModel(R.drawable.pic3,"blackberry updates its BBM app with support for Android 5.0 lollipop and it is amazing."));
        androidList.add(new AndroidCenterDataModel(R.drawable.pic4,"Motorola Droid Turbo pr Moto X (2014): Which should you buy?"));
        androidList.add(new AndroidCenterDataModel(R.drawable.pic1,"Google Play Newsstand expands its reach to Mexico and three more countries."));
        androidList.add(new AndroidCenterDataModel(R.drawable.pic2,"Googke maps add provides lane guidance to its users in European countries."));
        androidList.add(new AndroidCenterDataModel(R.drawable.pic3,"Nexus 5: Past, Present and future"));
        androidList.add(new AndroidCenterDataModel(R.drawable.pic4,"Nexus 9 with LTE now on sale in the google playe store"));
        AndroidCenterAdapter adapter = new AndroidCenterAdapter(this,androidList);
        gridView.setAdapter(adapter);
    }
}