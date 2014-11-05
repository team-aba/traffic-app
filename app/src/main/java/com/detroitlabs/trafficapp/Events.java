package com.detroitlabs.trafficapp;

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

    public void setEventDate(Date date){
        this.eventDate = date;
    }

    public void setEventStartTime(String startTime){
        this.eventStartTime = startTime;
    }

    public String getTime(){
        String time = "00:00";
        if(!dateAndTime.equals("")){
            time = dateAndTime.substring(11);
        }



/*
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy hh:mm a");
        SimpleDateFormat sdfDate = new SimpleDateFormat("MM-dd-yyyy");
        SimpleDateFormat sdfTime = new SimpleDateFormat("hh:mm a");

        try{
        Date dateObject = sdf.parse(dateAndTime);
            String dateobject = sdf.format(dateObject);
            Date dateOnly = sdfDate.parse(dateobject);
            setEventDate(dateOnly);
            Date timeOnly = sdfTime.parse(dateobject);
            time = sdfTime.format(timeOnly);
        }
        catch (ParseException e){
            Log.e("Parse Exception", e.getMessage());
        }
*/

        return time;
    }


}
