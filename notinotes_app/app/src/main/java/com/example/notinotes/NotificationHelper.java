package com.example.notinotes;

import android.app.Notification;
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
        if (isPersistent) {
            PendingIntent intent = PendingIntent.getActivity(context, 0, new Intent(context, NotifyActivity.class).putExtra("notificationId", notificationId), PendingIntent.FLAG_IMMUTABLE);
            Notification notify = new NotificationCompat.Builder(context, NOTIFICATION_CHANNEL_ID)
                    .setOngoing(true)
                    .setContentText(text)
                    .setSmallIcon(R.drawable.baseline_edit_note_24)
                    .setPriority(NotificationCompat.PRIORITY_MIN)
                    //.addAction(R.drawable.baseline_close_24, "Dismiss", intent)
                    .build();
            notify.flags = Notification.FLAG_ONGOING_EVENT;
            notificationManager.notify(notificationId, notify);
        } else {
            NotificationCompat.Builder notifyBuilder = new NotificationCompat.Builder(context, NOTIFICATION_CHANNEL_ID)
                    .setContentText(text)
                    .setPriority(NotificationCompat.PRIORITY_MIN)
                    .setSmallIcon(R.drawable.baseline_edit_note_24);
            notificationManager.notify(notificationId, notifyBuilder.build());
        }
    }

    public NotificationManager getNotificationManager() {
        return notificationManager;
    }
}
