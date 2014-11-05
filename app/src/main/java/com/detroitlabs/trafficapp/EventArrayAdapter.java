package com.detroitlabs.trafficapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by BFineRocks on 11/4/14.
 */
public class EventArrayAdapter extends ArrayAdapter<Events>{

    private final Context mContext;

    public ArrayList<Events> thisWeeksEvents;


    public EventArrayAdapter(Context context, ArrayList<Events> thisWeeksEvents) {
        super(context, R.layout.event_item, thisWeeksEvents);
        Log.d("ViewCalled", "array started");
        this.mContext = context;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View thisRow = inflater.inflate(R.layout.event_item, parent, false);
        Events thisObject = getItem(position);
        Log.d("ViewCalled", "view is called");
        thisWeeksEvents = ListViewFragment.mEventsArrayList;

        TextView eventTitle = (TextView) thisRow.findViewById(R.id.event_text);
        eventTitle.setText(thisObject.getEventName());

        TextView eventTime = (TextView) thisRow.findViewById(R.id.event_time);
        eventTime.setText(thisObject.getTime());

        return thisRow;

    }
}
