package com.example.api.ramsha.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.content.IntentFilter;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class BroadcastReceivers extends AppCompatActivity {

    private TextView systemBroadcastTextView;
    private TextView customBroadcastTextView;
    private BroadcastReceiver systemBroadcastReceiver;
    private BroadcastReceiver customBroadcastReceiver;
    Button btn, unregister, register;
    private static final String CUSTOM_ACTION = "com.example.custombroadcast.ACTION_CUSTOM_BROADCAST";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast_receivers);

        //finding views
        systemBroadcastTextView = findViewById(R.id.systemBroadcastTextView);
        customBroadcastTextView = findViewById(R.id.customBroadcastTextView);
        btn = findViewById(R.id.btn);
        unregister = findViewById(R.id.btnUnregister);
        register = findViewById(R.id.btnRegister);

        // System Broadcast Receiver
        systemBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (intent.getAction() != null && intent.getAction().equals(Intent.ACTION_POWER_CONNECTED)) {
                    systemBroadcastTextView.setText("Power Connected!");
                } else if (intent.getAction() != null && intent.getAction().equals(Intent.ACTION_POWER_DISCONNECTED)) {
                    systemBroadcastTextView.setText("Power Disconnected!");
                }
            }
        };
        // Custom Broadcast Receiver
        customBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (intent.getAction() != null && intent.getAction().equals(CUSTOM_ACTION)) {
                    String message = intent.getStringExtra("message");
                    customBroadcastTextView.setText(message);
                }
            }
        };

        //click Listeners
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Send Custom Broadcast
                Intent customIntent = new Intent(CUSTOM_ACTION);
                customIntent.putExtra("message", "Custom Broadcast Received!");
                sendBroadcast(customIntent);
            }
        });


        unregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    unregisterReceiver(systemBroadcastReceiver);
                    unregisterReceiver(customBroadcastReceiver);
                    customBroadcastTextView.setText("Custom Broadcast Receiver");
                    systemBroadcastTextView.setText("System Broadcast Receiver");
                    Toast.makeText(BroadcastReceivers.this, "Broadcast receivers unregistered", Toast.LENGTH_SHORT).show();

                } catch (Exception e) {
                    Toast.makeText(BroadcastReceivers.this, "" + e.getMessage(), Toast.LENGTH_SHORT).show();
                }

            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Register System Broadcast Receiver
                IntentFilter systemIntentFilter = new IntentFilter();
                systemIntentFilter.addAction(Intent.ACTION_POWER_CONNECTED);
                systemIntentFilter.addAction(Intent.ACTION_POWER_DISCONNECTED);
                registerReceiver(systemBroadcastReceiver, systemIntentFilter);
                // Register Custom Broadcast Receiver
                IntentFilter customIntentFilter = new IntentFilter(CUSTOM_ACTION);
                registerReceiver(customBroadcastReceiver, customIntentFilter);
                Toast.makeText(BroadcastReceivers.this, "Broadcast receivers registered", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Unregister the Broadcast Receivers
        unregisterReceiver(systemBroadcastReceiver);
        unregisterReceiver(customBroadcastReceiver);
    }
}
