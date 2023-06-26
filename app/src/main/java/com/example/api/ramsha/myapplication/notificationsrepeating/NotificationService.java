package com.example.api.ramsha.myapplication.notificationsrepeating;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.os.SystemClock;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import com.example.api.ramsha.myapplication.R;
import com.example.api.ramsha.myapplication.musicplayer.MusicPlayer;
import com.example.api.ramsha.myapplication.musicplayer.MusicService;

//public class NotificationService extends Service {
//    NotificationManager notificationManager;
//    private int notificationId = 0;
//
//
//    @Override
//    public void onCreate() {
//        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
//        notificationId+=1;
//        Log.d(TAG, "onCreate: "+notificationId);
//        startForeground(notificationId, createNotification());
//
//        super.onCreate();
//    }
//    private Notification createNotification() {
//
//
//       // Intent intent = new Intent(NotificationService.this, MusicPlayer.class);
//
//
//       // PendingIntent pendingIntent = PendingIntent.getActivity(NotificationService.this, 0, intent, PendingIntent.FLAG_MUTABLE);
//
//
//        NotificationCompat.Builder builder;
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            builder = new NotificationCompat.Builder(NotificationService.this, "channel_id");
//        } else {
//            builder = new NotificationCompat.Builder(NotificationService.this);
//        }
//
//        builder
//                .setSmallIcon(R.drawable.baseline_music_note_24)
//                .setCategory(NotificationCompat.CATEGORY_SERVICE)
//                //.setContentIntent(pendingIntent)
//                .setContentTitle("Repeating Notification")
//               // .setOngoing(true)
//                .setAutoCancel(true)
//                .setPriority(NotificationCompat.PRIORITY_HIGH); //setAutocancel dismisal the notification after one click
//        return builder.build();
//    }
//
//
//
//
//
//
//    @Nullable
//    @Override
//    public IBinder onBind(Intent intent) {
//        return null;
//    }
//}



public class NotificationService extends BroadcastReceiver {
    private static final int NOTIFICATION_ID = 1;
    private static final long ALARM_INTERVAL = 6 * 1000;
    private NotificationManager notificationManager;
    @Override
    public void onReceive(Context context, Intent intent) {
        notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        showNotification(context);
        scheduleAlarm(context);
    }


    private void showNotification(Context context) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "channel_id")
                .setSmallIcon(R.drawable.baseline_music_note_24)
                .setContentTitle("Repeating Notification")
                .setAutoCancel(true)
                .setPriority(NotificationCompat.PRIORITY_HIGH);

        notificationManager.notify(NOTIFICATION_ID, builder.build());
    }

    public static void scheduleAlarm(Context context) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, NotificationService.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_IMMUTABLE);

        long triggerTime = SystemClock.elapsedRealtime() + ALARM_INTERVAL;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            alarmManager.setExactAndAllowWhileIdle(AlarmManager.ELAPSED_REALTIME_WAKEUP, triggerTime, pendingIntent);
        } else {
            alarmManager.setExact(AlarmManager.ELAPSED_REALTIME_WAKEUP, triggerTime, pendingIntent);
        }
    }



}


