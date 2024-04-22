package com.example.notinotes;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;

public class NotificationHelper {
    private Context context;
    private NotificationManager notificationManager;
    private static final String NOTIFICATION_CHANNEL_ID = "0";

    public NotificationHelper(Context context) {
        this.context = context;
        createNotificationChannel();
    }

    private void createNotificationChannel() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, context.getString(R.string.channel_name), NotificationManager.IMPORTANCE_DEFAULT);
            channel.setShowBadge(false);
            channel.setBypassDnd(true);
            this.notificationManager = context.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    public void newNotification(String text, int notificationId, boolean isPersistent) {
        PendingIntent intent = PendingIntent.getActivity(context, 0, new Intent(context, NotifyActivity.class).putExtra("notificationId", notificationId), PendingIntent.FLAG_IMMUTABLE);
        NotificationCompat.Builder notifyBuilder;
        if (isPersistent) {
            notifyBuilder = new NotificationCompat.Builder(context, NOTIFICATION_CHANNEL_ID)
                    .setContentText(text)
                    .setSmallIcon(R.drawable.baseline_edit_note_24)
                    .setOngoing(true)
                    .addAction(R.drawable.baseline_close_24, "Dismiss", intent);
        } else {
            notifyBuilder = new NotificationCompat.Builder(context, NOTIFICATION_CHANNEL_ID)
                    .setContentText(text)
                    .setSmallIcon(R.drawable.baseline_edit_note_24);
        }
        notificationManager.notify(notificationId, notifyBuilder.build());
    }

    public NotificationManager getNotificationManager() {
        return notificationManager;
    }
}
