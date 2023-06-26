package com.example.api.ramsha.myapplication.musicplayer;

import static android.content.ContentValues.TAG;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;


import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.api.ramsha.myapplication.R;

public class MusicService extends Service {


    NotificationManager notificationManager;
    private int notificationId = 1;

    private MediaPlayer mediaPlayer;
    private boolean isPlaying = false;
    private final IBinder binder = new MusicBinder();

    @Override
    public void onCreate() {
        super.onCreate();
        mediaPlayer = MediaPlayer.create(this, R.raw.sample_music);
        mediaPlayer.setLooping(true);
        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (!isPlaying) {
            playMusic();
        }
        return START_NOT_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (isPlaying) {
            stopMusic();
        }
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    public void playMusic() {
        Toast.makeText(this, "heloooo", Toast.LENGTH_SHORT).show();

        if (mediaPlayer != null) {
            mediaPlayer.start();
            isPlaying = true;
            startForeground(notificationId, createNotification());
        }
    }

    public void pauseMusic() {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
            isPlaying = false;
        }
    }

    public void stopMusic() {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
            mediaPlayer.reset();
            isPlaying = false;
        }
    }

    private Notification createNotification() {


        Intent intent = new Intent(MusicService.this, MusicPlayer.class);


        PendingIntent pendingIntent = PendingIntent.getActivity(MusicService.this, 0, intent, PendingIntent.FLAG_MUTABLE);


        NotificationCompat.Builder builder;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            builder = new NotificationCompat.Builder(MusicService.this, "channel_id");
        } else {
            builder = new NotificationCompat.Builder(MusicService.this);
        }

        builder
                .setSmallIcon(R.drawable.baseline_music_note_24)
                .setCategory(NotificationCompat.CATEGORY_SERVICE)
                .setContentIntent(pendingIntent)
                .setContentTitle("Music Playing")
                .setOngoing(true)
                .setAutoCancel(true)
                .setPriority(NotificationCompat.PRIORITY_HIGH); //setAutocancel dismisal the notification after one click
        return builder.build();
    }

    public class MusicBinder extends Binder {
        public MusicService getService() {
            return MusicService.this;
        }
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O && notificationManager.getNotificationChannel("channel_id") == null) {
            CharSequence name = "My notification channel";
            String description = "My notification Channel description";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel("channel_id", name, importance);
            channel.setDescription(description);
            notificationManager.createNotificationChannel(channel);
        }
    }
}
