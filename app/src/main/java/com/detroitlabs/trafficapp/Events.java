package com.detroitlabs.trafficapp;

/**
 * Created by BFineRocks on 11/4/14.
 */
public class Events {
    String eventName;
    String eventDate;
    String eventStartTime;

    public Events(String eventName, String eventDate, String eventStartTime){
        this.eventName = eventName;
        this.eventDate = eventDate;
        this.eventStartTime = eventStartTime;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventStartTime() {
        return eventStartTime;
    }

    public void setEventStartTime(String eventStartTime) {
        this.eventStartTime = eventStartTime;
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }
}
