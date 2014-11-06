package com.detroitlabs.trafficapp;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;

/**
 * Created by aditishetty on 11/6/14.
 */
public class TrafficInfoWidgetProvider extends AppWidgetProvider {

    final static String WIDGET_UPDATE_ACTION = "com.example.detroitlabs.intent.action.UPDATE_WIDGET";

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);

        Log.v("onUpdate", "APPWIDGET");
        for (int i = 0; i < appWidgetIds.length; i++) {
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.traffic_info_widget);

            Intent intent = new Intent();
            intent.setAction(WIDGET_UPDATE_ACTION);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

            pushWidgetUpdate(context, views);

        }
    }

    public static void pushWidgetUpdate(Context context, RemoteViews remoteViews) {
        ComponentName myWidget = new ComponentName(context, TrafficInfoWidgetProvider.class);
        AppWidgetManager manager = AppWidgetManager.getInstance(context);
        manager.updateAppWidget(myWidget, remoteViews);
    }
}
