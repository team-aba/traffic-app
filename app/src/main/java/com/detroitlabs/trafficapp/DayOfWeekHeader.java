package com.detroitlabs.trafficapp;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.detroitlabs.trafficapp.ItemArrayAdapter.RowType;

/**
 * Created by BFineRocks on 11/11/14.
 */
public class DayOfWeekHeader implements Item {
    private final String name;

    public DayOfWeekHeader(String name){
        this.name = name;
    }

    @Override
    public int getViewType() {
        return RowType.HEADER_ITEM.ordinal();
    }

    @Override
    public View getView(LayoutInflater inflater, View viewConverter) {
        View view;
        if(viewConverter == null){
            view = (View) inflater.inflate(R.layout.day_of_week, null);
        }else{
            view = viewConverter;
        }
        TextView textView = (TextView) view.findViewById(R.id.day_of_week_item);
        textView.setText(name);

        return view;
    }
}
