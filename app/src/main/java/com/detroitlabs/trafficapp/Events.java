package com.detroitlabs.trafficapp;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.detroitlabs.trafficapp.ItemArrayAdapter.RowType;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * Created by BFineRocks on 11/4/14.
 */
public class Events implements Item {
    private String eventName;
    private String dateAndTime;
    private DateTime eventDate;
    private String eventStartTime;
    private String formattedName;
    private String formattedTime;
    private boolean isNoEvent;

    public Events(String eventName, String dateAndTime){
        this.eventName = eventName;
        this.dateAndTime = dateAndTime;

    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }


    public String getStringWholeDate(){
        return dateAndTime;
    }

    public void setStringWholeDate(String date){
        this.dateAndTime = date;
    }

    public void setEventDate(){
        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");

        dateTimeFormatter.withZone(DateTimeZone.forOffsetHours(-5));
       eventDate = dateTimeFormatter.parseDateTime(getStringWholeDate());

       // Log.i("eventDate", eventDate.toString());
    }
    public DateTime getEventDate(){
        return eventDate;
    }

    public void setEventStartTime(String startTime){
        this.eventStartTime = startTime;

    }

    public void setIsNoEvent(boolean isEvent){
        isNoEvent = isEvent;
    }

    public boolean isNoEvent(){
        return isNoEvent;
    }


    public String getTime(){
        String time = "00:00";
        if(!dateAndTime.equals("")){
            time = dateAndTime.substring(11);

        }

        return time;
    }

    public void formatNameAndTimeOfEvent(){
        String preTimeFormat = getTime();
        String ampm = "am";
        Integer cutTimeFormat = Integer.parseInt(preTimeFormat.substring(0,2));
        System.out.println(cutTimeFormat);
        if (cutTimeFormat > 12) {
            cutTimeFormat -= 12;
            ampm = "pm";
        }
        formattedTime = (cutTimeFormat + preTimeFormat.substring(2,5)+ ampm);

        String eventFormat = getEventName();
        int maxEventLength = 60;
        if (eventFormat.length() > maxEventLength)
            formattedName = (eventFormat.substring(0,maxEventLength)+"...");
    }


    @Override
    public int getViewType() {
        return RowType.LIST_ITEM.ordinal();
    }

    @Override
    public View getView(LayoutInflater inflater, View viewConverter) {
        View view;
        if(viewConverter == null){
            view = (View) inflater.inflate(R.layout.event_item, null);
        }else{
            view = viewConverter;
        }
        TextView textView = (TextView) view.findViewById(R.id.event_text);
        TextView timeView = (TextView) view.findViewById(R.id.event_time);

        textView.setText(formattedName);
        timeView.setText(formattedTime);

        return view;
    }
}
