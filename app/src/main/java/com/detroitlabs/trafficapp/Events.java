package com.detroitlabs.trafficapp;

import android.util.Log;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

/**
 * Created by BFineRocks on 11/4/14.
 */
public class Events {
    String eventName;
    String dateAndTime;
    Date eventDate;
    String eventStartTime;

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
        DateFormat dateFormat = DateFormat.getDateInstance();
        try{
        this.eventDate = dateFormat.parse(dateAndTime);}
        catch(ParseException e){
            Log.e("Parse Date Exception", e.getMessage());
        }

    }

    public Date getEventDate(){
        return eventDate;
    }

    public void setEventStartTime(String startTime){
        this.eventStartTime = startTime;

    }


    public String getTime(){
        String time = "00:00";
        if(!dateAndTime.equals("")){
            time = dateAndTime.substring(11);

            setEventDate();
            Log.i("SetEventDateCalled", "called");
        }



        return time;
    }


}
