package com.example.notinotes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity {
    private EditText inputNote;
    private Button button;
    private Switch persistentSwitch;

    public static final String PREF_FILE_NAME = "NotinotesPrefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Hooks
        inputNote = findViewById(R.id.inputNote);
        button = findViewById(R.id.button);
        persistentSwitch = findViewById(R.id.persistentSwitch);

        NotificationHelper helper = new NotificationHelper(this);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = inputNote.getText().toString().trim();
                helper.newNotification(text, getNextId(), persistentSwitch.isChecked());
                inputNote.setText("");
                finish();
            }
        });
    }

    private int getNextId() {
        SharedPreferences preferences = getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
        int nextId = preferences.getInt("notificationId", -1) + 1;
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("notificationId", nextId);
        editor.apply();
        return nextId;
    }
}