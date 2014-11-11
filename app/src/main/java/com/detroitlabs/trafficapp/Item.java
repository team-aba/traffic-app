package com.detroitlabs.trafficapp;

import android.view.LayoutInflater;
import android.view.View;

/**
 * Created by BFineRocks on 11/11/14.
 */
public interface Item {
    public int getViewType();
    public View getView(LayoutInflater inflater, View viewConverter);
}
