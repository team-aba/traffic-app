package com.detroitlabs.trafficapp;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by BFineRocks on 11/4/14.
 */
public class Events {
    String eventName;
    String dateAndTime;
    int eventDate;
    int eventStartTime;

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

    public String getTime(){
        String time = "";
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a");
        try{
        Date dateTime = sdf.parse(dateAndTime);
         time =  sdf.format(dateTime);
            Log.i("time", time);
        }
        catch (ParseException e){
            Log.e("Parse Exception", e.getMessage());
        }

        return time;
    }

}
