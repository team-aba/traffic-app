package com.detroitlabs.trafficapp;

import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;


public class ListViewFragment extends Fragment {
    ListView mListOfEvents;
    ArrayList<Events> mEventsArrayList = new ArrayList<Events>();



/*    public static ListViewFragment newInstance(String param1, String param2) {
        ListViewFragment fragment = new ListViewFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }*/
    public ListViewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView =  inflater.inflate(R.layout.fragment_list_view, container, false);
        mListOfEvents = (ListView) rootView.findViewById(R.id.list_view_of_events);


        return rootView;
    }


    public static class CheckEventsUpcoming extends AsyncTask<String, Void, String>{


        @Override
        protected String doInBackground(String... strings) {

            final static String URI_BASE = 

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }
    }





}
