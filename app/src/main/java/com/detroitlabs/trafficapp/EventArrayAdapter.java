package com.detroitlabs.trafficapp;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.List;

/**
 * Created by BFineRocks on 11/4/14.
 */
public class EventArrayAdapter extends ArrayAdapter<Events>{


    public EventArrayAdapter(Context context, int resource, List<Events> objects) {
        super(context, resource, objects);
    }
}
