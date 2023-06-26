package com.example.api.ramsha.myapplication.notificationsrepeating;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.api.ramsha.myapplication.R;

public class Notification extends AppCompatActivity {
    private static final long DELAY_TIME_MS = 6 * 1000; // 10 seconds
    private PendingIntent pendingIntent;
    private AlarmManager alarmManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        Intent intent = new Intent(this, NotificationService.class);
        pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_IMMUTABLE);

        Button startButton = findViewById(R.id.start);
        Button stopButton = findViewById(R.id.stop);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Notification.this, "Repeating Notification started", Toast.LENGTH_SHORT).show();
                NotificationService.scheduleAlarm(getApplicationContext());
                startRepeatingNotification();
            }
        });

        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                Intent intent = new Intent(getApplicationContext(), NotificationService.class);
                PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, intent, PendingIntent.FLAG_IMMUTABLE);
                alarmManager.cancel(pendingIntent);
                notificationManager.cancel(1);
                Toast.makeText(Notification.this, "Notification stopped", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void startRepeatingNotification() {
        // Schedule the recurring alarm
        long triggerTime = SystemClock.elapsedRealtime() + DELAY_TIME_MS;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Log.d(TAG, "hiiiii");
           alarmManager.setExactAndAllowWhileIdle(AlarmManager.ELAPSED_REALTIME_WAKEUP, triggerTime, pendingIntent);
        } else {
            Log.d(TAG, "hiiiii2");
            alarmManager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, triggerTime, DELAY_TIME_MS, pendingIntent);
        }

        Toast.makeText(this, "Repeating notification started", Toast.LENGTH_SHORT).show();
    }


}
