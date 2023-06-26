package com.example.api.ramsha.myapplication.notificationrepeatingjobscheduler;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.job.JobInfo;
import android.app.job.JobParameters;
import android.app.job.JobScheduler;
import android.app.job.JobService;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;

import androidx.core.app.NotificationCompat;

import com.example.api.ramsha.myapplication.R;

public class NotificationJobService extends JobService {

    private static final int NOTIFICATION_ID = 1;
    private NotificationManager notificationManager;

    @SuppressLint("NewApi")
    @Override
    public boolean onStartJob(JobParameters params) {
        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        showNotification();
        rescheduleJob();
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        return true;
    }

    private void showNotification() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "channel_id")
                .setSmallIcon(R.drawable.baseline_music_note_24)
                .setContentTitle("Repeating Notification")
                .setAutoCancel(true)
                .setPriority(NotificationCompat.PRIORITY_HIGH);

        notificationManager.notify(NOTIFICATION_ID, builder.build());
    }

    private void rescheduleJob() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            JobScheduler jobScheduler = (JobScheduler) getSystemService(Context.JOB_SCHEDULER_SERVICE);
            JobInfo.Builder builder = new JobInfo.Builder(1, new ComponentName(this, NotificationJobService.class))
                    .setMinimumLatency(10000) // 10 seconds
                    .setOverrideDeadline(20000) // 20 seconds
                    .setPersisted(true);

            if (jobScheduler != null) {
                jobScheduler.schedule(builder.build());
            }
        }
    }
}
