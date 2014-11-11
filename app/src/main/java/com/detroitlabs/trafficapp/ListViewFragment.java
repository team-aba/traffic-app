package com.detroitlabs.trafficapp;

import android.app.Fragment;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import org.joda.time.DateTime;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class ListViewFragment extends Fragment {
    ListView mListOfEvents;
    public static ArrayList<Events> mEventsArrayList = new ArrayList<Events>();
    public static List<Item> eventItems = new ArrayList<Item>();
    ItemArrayAdapter itemArrayAdapter;
    EventArrayAdapter mEventArrayAdapter;
    String eventDay;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        updateEvents();
        mEventArrayAdapter = new EventArrayAdapter(getActivity(), mEventsArrayList);
    }

    /*    public static ListViewFragment newInstance(String param1, String param2) {
            ListViewFragment fragment = new ListViewFragment();
            Bundle args = new Bundle();
            args.putString(ARG_PARAM1, param1);
            args.putString(ARG_PARAM2, param2);
            fragment.setArguments(args);
            return fragment
        }*/
    public ListViewFragment() {
        // Required empty public constructor
    }


    @Override
    public void onStart() {
        super.onStart();
      //  updateEvents();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView =  inflater.inflate(R.layout.fragment_list_view, container, false);
        mListOfEvents = (ListView) rootView.findViewById(R.id.list_view_of_events);
        mListOfEvents.setAdapter(itemArrayAdapter);

        //View headerView = (View) inflater.inflate(R.layout.day_of_week, null);
        //mListOfEvents.addHeaderView(headerView);
 /*       mListOfEvents.setAdapter(mEventArrayAdapter);
        mListOfEvents.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                try {
                    String geoUri = "geo:42.335416,-83.049161";
                    Intent mapApp = new Intent(Intent.ACTION_VIEW);
                    mapApp.setData(Uri.parse(geoUri));
                    startActivity(mapApp);
                } catch (ActivityNotFoundException e) {

                    String urlForMap = "https://www.google.com/maps/place/@42.335416,-83.049161,17z/data=!5m1!1e1";

                    Intent goToGoogleMaps = new Intent(Intent.ACTION_VIEW);
                    goToGoogleMaps.setData(Uri.parse(urlForMap));
                    startActivity(goToGoogleMaps);
                }

            }
        });*/

        return rootView;
    }

    public void updateEvents(){
         DateTime dateTime = DateTime.now();
         String todayDate = getDateInString(dateTime);
        dateTime.plusDays(7);
        String dateIn1Week = getDateInString(dateTime);

        String dateToRange = todayDate + "-" + dateIn1Week;
        Log.i("dateRange", dateToRange);

        new CheckEventsUpcoming().execute(dateToRange);

    }

    public String getDateInString(DateTime dateTime){
        String yearToday = String.valueOf(dateTime.getYear());
        String today = String.valueOf(dateTime.getDayOfMonth());
        String monthToday = String.valueOf(dateTime.getMonthOfYear());
        return yearToday + monthToday + today + "00";
    }


    public class CheckEventsUpcoming extends AsyncTask<String, Void, String[][]>{


        @Override
        protected String[][] doInBackground(String... strings) {

            final String MALFORMED_URL_ERROR = "Malformed Url Exception";
            final String IO_EXCEPTION_ERROR = "IO Exception";
            final String JSON_EXCEPTION_ERROR = "JSON Exception";
            HttpURLConnection mHttpURLConnection = null;
            BufferedReader mBufferedReader = null;
            String jsonString =  null;

            final String URI_BASE = "http://api.eventful.com/json/events/search?app_key=vc57D4w3FMkJfN4r"
                    + "&category=sports&location=42.335533,-83.0491771&within=05&units=mi";
            final String PARAM_DATE = "date";

            Log.i("date", strings[0]);

            try {

                Uri aUri = Uri.parse(URI_BASE).buildUpon()
                        .appendQueryParameter(PARAM_DATE, strings[0])
                        .build();

                URL urlToCall = new URL(aUri.toString());

                mHttpURLConnection = (HttpURLConnection) urlToCall.openConnection();
                mHttpURLConnection.setRequestMethod("GET");
                mHttpURLConnection.connect();

                InputStream inputStream = mHttpURLConnection.getInputStream();
                StringBuffer stringBuffer = new StringBuffer();

                if(inputStream == null){
                    return null;
                }

                mBufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String line;
                while((line = mBufferedReader.readLine()) != null){
                    stringBuffer.append(line + "\n");
                }
                if(stringBuffer.length() == 0){
                    return  null;
                }

                jsonString = stringBuffer.toString();


            }catch(MalformedURLException e){
                Log.e(MALFORMED_URL_ERROR, e.getMessage());
            }
            catch(IOException e){
                Log.e(IO_EXCEPTION_ERROR, e.getMessage());
            }
            finally {
                if(mHttpURLConnection != null){
                    mHttpURLConnection.disconnect();
                }
                if(mBufferedReader !=null){
                    try{
                    mBufferedReader.close();}

                    catch(IOException e){
                        Log.e(IO_EXCEPTION_ERROR, e.getMessage());
                    }
                }
            }
            try {

                return createEventsFromJSONData(jsonString);
            }catch(JSONException e){
                Log.e(JSON_EXCEPTION_ERROR, e.getMessage());
            }

            return null;
        }


        private String[][] createEventsFromJSONData(String jsondata) throws JSONException{

            final String EVENTFUL_LIST = "event";
            final String START_TIME = "start_time";
            final String EVENT_TITLE ="title";


            JSONObject jsonObject = new JSONObject(jsondata);
            Log.i("jsonData", jsondata);
            JSONObject eventsObject = jsonObject.getJSONObject("events");
            Log.i("eventsData", eventsObject.toString());
            JSONArray arrayOfEvents = eventsObject.getJSONArray(EVENTFUL_LIST);
            String[][] eventsArray = new String[arrayOfEvents.length()][2];

            for(int i = 0; i < arrayOfEvents.length(); i++){

                String title;
                String date;
                int j = 0;
                JSONObject eventObject = arrayOfEvents.getJSONObject(i);
                title = eventObject.getString(EVENT_TITLE);
                eventsArray[i][j] = title;
                Log.i("eventsArray", eventsArray[i][j]);
                j++;
                date = eventObject.getString(START_TIME);
                eventsArray[i][j] = date;


            }
            return  eventsArray;

        }



        @Override
        protected void onPostExecute(String[][] events) {
            super.onPostExecute(events);
            if(events == null){
                Events noEvents = new Events("No Events", "");
                noEvents.setIsNoEvent(true);
                mEventsArrayList.add(noEvents);

            }
            if(events != null){
            String title = "";
            String time = "";
            Log.i("eventsLength", String.valueOf(events.length));
            for(int i = 0; i < events.length; i++) {
                int j = 0;
                title = events[i][j];
               Log.i("eventTitle", title);
                time = events[i][j+1];
               Log.i("eventTime", time);

                Events anEvent = new Events(title, time);
                anEvent.setEventDate();
                mEventsArrayList.add(anEvent);
                Log.i("eventAddedToArrayList", mEventsArrayList.get(i).getEventName());

            }}

            Collections.sort(mEventsArrayList, EventSorter);
            sortEventsAndAddToItemArray(mEventsArrayList);


     //       mEventArrayAdapter.addAll(mEventsArrayList);
            //Log.i("eventsAdded", mEventsArrayList.get(i).getEventName());

        }

        public void sortEventsAndAddToItemArray(ArrayList<Events> eventArray){

            for(int i = 0; i < eventArray.size(); i++){
                Events event = eventArray.get(i);
                if(!event.isNoEvent()){
                   eventItems.add(createDayOfWeekHeader(getDayOfWeek(event)));
                    eventItems.add(event);
                }
                else{
                    eventItems.add(createDayOfWeekHeader("No Events Found"));
                }
            }
            itemArrayAdapter = new ItemArrayAdapter(getActivity(), eventItems);
            mListOfEvents.setAdapter(itemArrayAdapter);

        }

        public DayOfWeekHeader createDayOfWeekHeader(String dayofWeek){

           return new DayOfWeekHeader(dayofWeek);
        }

        public String getDayOfWeek(Events event){
            String weekDay = "";
          //  if(!event.isNoEvent()) {
                switch (event.getEventDate().getDayOfWeek()) {
                    case 1:
                        weekDay = "Monday";
                        break;
                    case 2:
                        weekDay = "Tuesday";
                        break;
                    case 3:
                        weekDay = "Wednesday";
                        break;
                    case 4:
                        weekDay = "Thursday";
                        break;
                    case 5:
                        weekDay = "Friday";
                        break;
                    case 6:
                        weekDay = "Saturday";
                        break;
                    case 7:
                        weekDay = "Sunday";
                        break;
                }
        //    }
            return weekDay;
        }



        public Comparator<Events> EventSorter = new Comparator<Events>() {
            @Override
            public int compare(Events events, Events events2) {
                String dateEvent = events.getStringWholeDate();
                String dateEvent2 = events2.getStringWholeDate();

                return dateEvent.compareTo(dateEvent2);
            }
        };


    }










}
