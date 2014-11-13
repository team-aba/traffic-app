package com.detroitlabs.trafficapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import java.util.ArrayList;

/**
 * Created by aditishetty on 11/6/14.
 */
public class InfoWidgetIntentReceiver extends BroadcastReceiver {

    final static String WIDGET_UPDATE_ACTION = "com.example.detroitlabs.intent.action.UPDATE_WIDGET";

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(WIDGET_UPDATE_ACTION)) {
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.traffic_info_widget);

            ArrayList<Events> mEventsArrayList = new ArrayList<Events>();
            views.setTextViewText(R.id.trafficAnswerWidget, mEventsArrayList.get(0).getEventName());

            TrafficInfoWidgetProvider.pushWidgetUpdate(context, views);
        }
    }
}
