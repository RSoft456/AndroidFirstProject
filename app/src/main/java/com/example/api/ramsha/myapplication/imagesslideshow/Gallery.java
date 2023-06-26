package com.example.api.ramsha.myapplication.imagesslideshow;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.api.ramsha.myapplication.R;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import java.util.ArrayList;
import java.util.List;

public class Gallery extends AppCompatActivity {

    private static final int REQUEST_CAMERA_PERMISSION = 1;
    private static final int REQUEST_CAMERA_CAPTURE = 2;
    private static final int REQUEST_GALLERY_PICK = 3;

    private ViewPager viewPager;
    private ImagePagerAdapter adapter;
    private List<Bitmap> images;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        viewPager = findViewById(R.id.viewPager);
        Button captureButton = findViewById(R.id.captureButton);
        Button galleryButton = findViewById(R.id.galleryButton);

        images = new ArrayList<>();
        adapter = new ImagePagerAdapter(images,this);
        viewPager.setAdapter(adapter);

        captureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkCameraPermission()) {
                    captureImageFromCamera();
                } else {
                    requestCameraPermission();
                }
            }
        });

        galleryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickImageFromGallery();
            }
        });
    }

    private boolean checkCameraPermission() {
        int result = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA);
        return result == PackageManager.PERMISSION_GRANTED;
    }

    private void requestCameraPermission() {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, REQUEST_CAMERA_PERMISSION);
    }

    private void captureImageFromCamera() {
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, REQUEST_CAMERA_CAPTURE);
    }

    private void pickImageFromGallery() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, REQUEST_GALLERY_PICK);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_CAMERA_CAPTURE) {
                Bitmap photo = (Bitmap) data.getExtras().get("data");
                images.add(photo);
                adapter.notifyDataSetChanged();
            } else if (requestCode == REQUEST_GALLERY_PICK) {
                try {
                    Bitmap selectedImage;
                    if (data != null && data.getData() != null) {
                        selectedImage = MediaStore.Images.Media.getBitmap(getContentResolver(), data.getData());

                        images.add(selectedImage);
                        adapter.notifyDataSetChanged();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
