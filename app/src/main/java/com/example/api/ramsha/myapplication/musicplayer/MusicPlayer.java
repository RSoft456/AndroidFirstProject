package com.example.api.ramsha.myapplication.musicplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.api.ramsha.myapplication.R;

public class MusicPlayer extends AppCompatActivity {
Button start,stop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_player);
        start=findViewById(R.id.btnStart);
        stop=findViewById(R.id.btnStop);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MusicPlayer.this, MusicService.class);
                startService(intent);
            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MusicPlayer.this, MusicService.class);
                stopService(intent);
            }
        });
    }
    public void startMusicService(View view) {

    }

    public void stopMusicService(View view) {
        Intent intent = new Intent(this, MusicService.class);
        stopService(intent);
    }




}
