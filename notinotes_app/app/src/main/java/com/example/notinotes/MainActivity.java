package com.example.notinotes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity {
    private EditText inputNote;
    private Button button;
    private Switch persistentSwitch;
    private static int NOTIFICATION_ID = 0;

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
                helper.newNotification(text, NOTIFICATION_ID, persistentSwitch.isChecked());
                NOTIFICATION_ID++;
                inputNote.setText("");
                finish();
            }
        });
    }


}