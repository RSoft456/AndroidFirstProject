package com.example.api.ramsha.myapplication.notificationrepeatingjobscheduler;

import androidx.appcompat.app.AppCompatActivity;

import android.app.NotificationManager;
import android.os.Bundle;

import com.example.api.ramsha.myapplication.R;
import com.example.api.ramsha.myapplication.notificationsrepeating.Notification;


import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;

import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Notification_JobScheduler extends AppCompatActivity {
    Button startButton, stopButton;
    private static final int JOB_ID = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_job_scheduler);
        startButton = findViewById(R.id.startbutton);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        stopButton = findViewById(R.id.stopbutton);


        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startJobScheduler();
                Toast.makeText(Notification_JobScheduler.this, "Repeating Notification started", Toast.LENGTH_SHORT).show();

            }
        });

        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopJobScheduler();
                notificationManager.cancel(1);
                Toast.makeText(Notification_JobScheduler.this, "Notification stopped", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void startJobScheduler() {
        JobScheduler jobScheduler = (JobScheduler) getSystemService(Context.JOB_SCHEDULER_SERVICE);

        if (jobScheduler != null) {
            ComponentName componentName = new ComponentName(this, NotificationJobService.class);
            JobInfo jobInfo = new JobInfo.Builder(JOB_ID, componentName)
                    .setPersisted(true)
                    .setMinimumLatency(10000) // 10 seconds
                    .setOverrideDeadline(20000) // 20 seconds
                    .build();

            jobScheduler.schedule(jobInfo);
        }
    }

    private void stopJobScheduler() {
        JobScheduler jobScheduler = (JobScheduler) getSystemService(Context.JOB_SCHEDULER_SERVICE);
        if (jobScheduler != null) {
            jobScheduler.cancel(JOB_ID);
        }
    }
}
