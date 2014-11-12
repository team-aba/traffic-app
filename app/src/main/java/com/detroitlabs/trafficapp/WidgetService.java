package com.detroitlabs.trafficapp;

import android.app.IntentService;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.widget.RemoteViews;

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

/**
 * Created by aditishetty on 11/12/14.
 */
public class WidgetService extends IntentService {

    public WidgetService(){
        super("WidgetService");
    }

    protected void sendEventRequest(String... strings) {

        final String MALFORMED_URL_ERROR = "Malformed Url Exception";
        final String IO_EXCEPTION_ERROR = "IO Exception";
        final String JSON_EXCEPTION_ERROR = "JSON Exception";
        HttpURLConnection mHttpURLConnection = null;
        BufferedReader mBufferedReader = null;
        String jsonString =  null;
        String [][] events = null;

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

            mBufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while((line = mBufferedReader.readLine()) != null){
                stringBuffer.append(line + "\n");
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
            events = createEventsFromJSONData(jsonString);
        }catch(JSONException e){
            Log.e(JSON_EXCEPTION_ERROR, e.getMessage());
        }

        ArrayList<Events> mEventsArrayList = new ArrayList<Events>();

        if(events == null){
            Events noEvents = new Events("No Events", "");
            noEvents.setIsNoEvent(true);
            mEventsArrayList.add(noEvents);

        }
        else {
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

        RemoteViews views = new RemoteViews(getPackageName(), R.layout.traffic_info_widget);
        views.setTextViewText(R.id.trafficAnswerWidget, mEventsArrayList.get(0).getEventName());


        ComponentName widget = new ComponentName(this, TrafficInfoWidgetProvider.class);

        AppWidgetManager.getInstance(getApplicationContext()).updateAppWidget(widget, views);

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
    protected void onHandleIntent(Intent intent) {
        sendEventRequest(new String [] {"2014111200-201521200"});
    }
}
