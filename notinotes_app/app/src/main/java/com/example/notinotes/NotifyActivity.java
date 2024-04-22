package com.example.notinotes;

import androidx.appcompat.app.AppCompatActivity;

import android.app.NotificationManager;
import android.os.Bundle;

public class NotifyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        NotificationHelper notificationHelper = new NotificationHelper(this);
        notificationHelper.getNotificationManager().cancel(getIntent().getExtras().getInt("notificationId", -1));
        finish();
    }
}