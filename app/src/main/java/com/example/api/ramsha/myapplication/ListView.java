package com.example.api.ramsha.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.ListAdapter;

import java.util.ArrayList;

public class ListView extends AppCompatActivity {
    android.widget.ListView listView;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        toolbar=findViewById(R.id.toolbarListView);
        toolbar.setNavigationIcon(R.drawable.baseline_arrow_back_24);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        ArrayList<FruitsDataModel> alphabetList = new ArrayList<FruitsDataModel>();
        alphabetList.add(new FruitsDataModel("Apple","Apple color is red.",R.drawable.apple));
        alphabetList.add(new FruitsDataModel("Banana","Banana color is yellow.",R.drawable.banana));
        alphabetList.add(new FruitsDataModel("Orange","Nagpur is famous for orange, that's why it is",R.drawable.orange));
        alphabetList.add(new FruitsDataModel("Mango","Mango is king of fruits.",R.drawable.mango));
        alphabetList.add(new FruitsDataModel("Papaya","Papaya is best for skin.",R.drawable.papaya));
        alphabetList.add(new FruitsDataModel("Apple","Apple color is red.",R.drawable.apple));
        alphabetList.add(new FruitsDataModel("Banana","Banana color is yellow.",R.drawable.banana));
        alphabetList.add(new FruitsDataModel("Orange","Nagpur is famous for orange, that's why it is",R.drawable.orange));
        alphabetList.add(new FruitsDataModel("Mango","Mango is king of fruits.",R.drawable.mango));
        alphabetList.add(new FruitsDataModel("Papaya","Papaya is best for skin.",R.drawable.papaya));
        listView = findViewById(R.id.alphabetListView);
        FruitsAdapter adapter = new FruitsAdapter(this, alphabetList);
        listView.setAdapter(adapter);


    }
}