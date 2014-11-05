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
import java.util.Calendar;


public class ListViewFragment extends Fragment {
    ListView mListOfEvents;
    public static ArrayList<Events> mEventsArrayList = new ArrayList<Events>();




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
    public void onStart() {
        super.onStart();
        updateEvents();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView =  inflater.inflate(R.layout.fragment_list_view, container, false);
        mListOfEvents = (ListView) rootView.findViewById(R.id.list_view_of_events);


        return rootView;
    }

    public void updateEvents(){
         Calendar currentCal = Calendar.getInstance();
         String todayDate = getDateInString(currentCal);
        currentCal.add(Calendar.WEEK_OF_YEAR, 1);
        String dateIn1Week = getDateInString(currentCal);

        String dateToRange = todayDate + "-" + dateIn1Week;

        new CheckEventsUpcoming().execute(dateToRange);

    }

    public String getDateInString(Calendar calendar){
        String yearToday = String.valueOf(calendar.get(Calendar.YEAR));
        String today = String.valueOf(calendar.get(Calendar.DATE));
        String monthToday = String.valueOf(calendar.get(Calendar.MONTH));
        return yearToday + monthToday + today + "00";
    }


    public class CheckEventsUpcoming extends AsyncTask<String, Void, Void>{


        @Override
        protected Void doInBackground(String... strings) {

            final String MALFORMED_URL_ERROR = "Malformed Url Exception";
            final String IO_EXCEPTION_ERROR = "IO Exception";
            final String JSON_EXCEPTION_ERROR = "JSON Exception";
            HttpURLConnection mHttpURLConnection = null;
            BufferedReader mBufferedReader = null;
            String jsonString =  null;

            final String URI_BASE = "http://api.eventful.com/json/events/search?app_key=vc57D4w3FMkJfN4r&location=42.335533,-83.0491771&within=30&units=mi";
            final String PARAM_DATE = "date";

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

                createEventsFromJSONData(jsonString);
            }catch(JSONException e){
                Log.e(JSON_EXCEPTION_ERROR, e.getMessage());
            }

            return null;
        }


        private void createEventsFromJSONData(String jsondata) throws JSONException{

            final String EVENTFUL_LIST = "events";
            final String START_TIME = "start_time";
            final String EVENT_TITLE ="title";

            JSONObject jsonObject = new JSONObject(jsondata);
            JSONArray arrayOfEvents = jsonObject.getJSONArray(EVENTFUL_LIST);
            for(int i = 0; i < arrayOfEvents.length(); i++){

                String title;
                String date;

                JSONObject eventObject = arrayOfEvents.getJSONObject(i);
                title = eventObject.getString(EVENT_TITLE);
                date = eventObject.getString(START_TIME);
                Events anEvent = new Events(title, date);
                mEventsArrayList.add(anEvent);

            }


        }


/*

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }
*/

    }





}
