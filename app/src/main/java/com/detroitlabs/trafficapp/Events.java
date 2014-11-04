package com.detroitlabs.trafficapp;

/**
 * Created by BFineRocks on 11/4/14.
 */
public class Events {
    String eventName;
    String eventStartTime;

    public Events(String eventName, String eventStartTime){
        this.eventName = eventName;
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
}
