package com.detroitlabs.trafficapp;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class BroadcastNotification extends BroadcastReceiver {

    NotificationManager nm;

    @Override
    public void onReceive(Context context, Intent intent) {
        nm = (NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE);
        CharSequence from = "TrafficApp";
        CharSequence message = "A thing is happening";
        PendingIntent contentIntent = PendingIntent.getActivity(context, 0,
                new Intent(), 0);
        Notification notif = new Notification(R.drawable.ic_launcher,
                "That thing is happening" , System.currentTimeMillis());
        notif.setLatestEventInfo(context, from, message, contentIntent);
        nm.notify(1, notif);
    }
}
