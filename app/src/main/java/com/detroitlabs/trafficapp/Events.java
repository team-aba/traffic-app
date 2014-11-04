package com.detroitlabs.trafficapp;

import java.util.Date;

/**
 * Created by BFineRocks on 11/4/14.
 */
public class Events {
    String eventName;
    Date date;

    public Events(String eventName, Date date){
        this.eventName = eventName;
        this.date = date;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }


    public Date getDate(){
        return date;
    }

    public void setDate(Date date){
        this.date = date;
    }
}
