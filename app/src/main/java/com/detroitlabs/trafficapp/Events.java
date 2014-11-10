package com.detroitlabs.trafficapp;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * Created by BFineRocks on 11/4/14.
 */
public class Events {
    String eventName;
    String dateAndTime;
    DateTime eventDate;
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


    public String getTime(){
        String time = "00:00";
        if(!dateAndTime.equals("")){
            time = dateAndTime.substring(11);

        }

        return time;
    }


}
