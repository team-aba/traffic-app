package com.detroitlabs.trafficapp;

import android.content.BroadcastReceiver;
import android.content.Context
import android.content.Intent;
import android.widget.RemoteViews;

/**
 * Created by aditishetty on 11/6/14.
 */
public class InfoWidgetIntentReceiver extends BroadcastReceiver {

    final static String WIDGET_UPDATE_ACTION = "com.example.detroitlabs.intent.action.UPDATE_WIDGET";

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(WIDGET_UPDATE_ACTION)) {
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.traffic_info_widget);

            views.setTextViewText(R.id.trafficAnswerWidget, "Refreshed");

            TrafficInfoWidgetProvider.pushWidgetUpdate(context, views);
        }
    }
}
