package com.example.api.ramsha.myapplication.listview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.api.ramsha.myapplication.R;

import java.util.ArrayList;

public class FruitsAdapter extends ArrayAdapter<FruitsDataModel>{

    public FruitsAdapter(@NonNull Context context, ArrayList<FruitsDataModel> StudentList) {
        super(context, R.layout.item_fruits, StudentList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        FruitsDataModel list = getItem(position);
        convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_fruits, parent, false);

        TextView name = convertView.findViewById(R.id.name);
        TextView description = convertView.findViewById(R.id.description);
        ImageView image = convertView.findViewById(R.id.fruitImage);

        name.setText(list.getName());
        description.setText(list.getDescription());
        image.setImageResource(list.getImageid());
        return convertView;

    }


}
