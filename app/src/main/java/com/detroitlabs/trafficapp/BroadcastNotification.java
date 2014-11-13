package com.detroitlabs.trafficapp;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import org.joda.time.DateTime;
import org.joda.time.JodaTimePermission;
import org.joda.time.format.DateTimeFormat;

public class BroadcastNotification extends BroadcastReceiver {

    NotificationManager nm;

    @Override
    public void onReceive(Context context, Intent intent) {
        nm = (NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE);
        CharSequence from = ListViewFragment.mEventsArrayList.get(0).getEventName();
        CharSequence message = "Time: "+ListViewFragment.mEventsArrayList.get(0).getTime();
        PendingIntent contentIntent = PendingIntent.getActivity(context, 0,
                new Intent(), 0);
        Notification notif = new Notification(R.drawable.ic_launcher,
                "An event is starting" , System.currentTimeMillis());
        notif.setLatestEventInfo(context, from, message, contentIntent);
        nm.notify(1, notif);
    }

    public static void makeAlarms(Context context){
        //long triggerAlarmTime = (System.currentTimeMillis()+150);
        String parseTimeForMillis = String.valueOf(ListViewFragment.mEventsArrayList.get(0).getEventDate());
        parseTimeForMillis = parseTimeForMillis.substring(0,10);
        parseTimeForMillis = parseTimeForMillis + " " + String.valueOf(ListViewFragment.mEventsArrayList.get(0).getFormattedTime());

        DateTime triggerAlarmTime = (DateTime.parse(parseTimeForMillis, DateTimeFormat.forPattern("yyyy-MM-dd H:mmaa")));
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        Intent alarmIntent = new Intent(context, BroadcastNotification.class);
        alarmIntent.putExtra("Title", "This Event");

        PendingIntent alarmPendingIntent = PendingIntent.getBroadcast(context, 0, alarmIntent, PendingIntent.FLAG_CANCEL_CURRENT);
        alarmManager.set(AlarmManager.RTC_WAKEUP, triggerAlarmTime.getMillis(), alarmPendingIntent);
    }
}
