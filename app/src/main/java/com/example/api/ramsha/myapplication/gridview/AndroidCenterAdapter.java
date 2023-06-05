package com.example.api.ramsha.myapplication.gridview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.api.ramsha.myapplication.R;
import com.example.api.ramsha.myapplication.listview.FruitsDataModel;

import java.util.ArrayList;

public class AndroidCenterAdapter extends ArrayAdapter<AndroidCenterDataModel> {

    public AndroidCenterAdapter(@NonNull Context context, ArrayList<AndroidCenterDataModel> AndroidCenterList) {
        super(context, R.layout.item_android_center, AndroidCenterList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        AndroidCenterDataModel list = getItem(position);
        convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_android_center, parent, false);

        TextView overlayText = convertView.findViewById(R.id.overlayText);
        ImageView image = convertView.findViewById(R.id.gridImages);

        overlayText.setText(list.getHeadline());
        image.setImageResource(list.getId());
        return convertView;
    }
}
