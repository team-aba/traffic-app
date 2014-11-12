package com.detroitlabs.trafficapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.List;

/**
 * Created by BFineRocks on 11/11/14.
 */
public class ItemArrayAdapter extends ArrayAdapter<Item> {

    private LayoutInflater viewInflater;

    public enum RowType{
        HEADER_ITEM, LIST_ITEM
    }

    public ItemArrayAdapter(Context context, List<Item> items){
        super(context, 0, items);
        viewInflater = LayoutInflater.from(context);
    }


    @Override
    public int getItemViewType(int position) {
        return getItem(position).getViewType();
    }

    @Override
    public int getViewTypeCount() {
        return RowType.values().length;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getItem(position).getView(viewInflater, convertView);
    }
}
