package com.detroitlabs.trafficapp;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

/**
 * Created by BFineRocks on 11/4/14.
 */
public class EventArrayAdapter extends ArrayAdapter<Events>{

    private final Context mContext;

    public ArrayList<Events> thisWeeksEvents;


    public EventArrayAdapter(Context context, int resource, ArrayList<Events> thisWeeksEvents) {
        super(context, resource, thisWeeksEvents);
        this.mContext = context;
    }
}
