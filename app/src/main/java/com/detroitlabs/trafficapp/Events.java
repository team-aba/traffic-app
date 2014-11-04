package com.detroitlabs.trafficapp;

/**
 * Created by BFineRocks on 11/4/14.
 */
public class Events {
    String eventName;
    String date;
    int eventDate;
    int eventStartTime;

    public Events(String eventName, String date){
        this.eventName = eventName;
        this.date = date;

    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }


    public String getDate(){
        return date;
    }

    public void setDate(String date){
        this.date = date;
    }
}
