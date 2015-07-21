package com.localexample;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.localexample.website_analyser.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity_technology extends Activity {

    private ProgressDialog pDialog;
    List<ListItem_technology> dataList_technology;
    Handler handler;
    private ListView resultList_technology;
    ArrayList<HashMap<String, String>> myResultList_technology;
    CustomListAdapter_technology adapter_technology;
    // URL to get contacts JSON
    private static String url = "https://apiv2dev.rankwatch.com/wa/wadetails/json/";
    // JSON Node names
    String ip_tech ,city_tech,region_tech,country_tech,hosting_provider;


    String b_rate="22";
    private static final String CITY="cf" ;
    private static final String REGION ="ds";
    private static final String COUNTRY ="ff";

    private static final String IP = "zvs";
    private static final String HOSTING_PROVIDER = "gre";
    private static final String TECH_USED = "tech";

    ArrayList<String> measureList;

    // contacts JSONArray
    JSONArray result = null;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_technology);
        myResultList_technology = new ArrayList<HashMap<String, String>>();


        new GetMeasuredData_technology().execute();
        dataList_technology = new ArrayList<>();
        resultList_technology= (ListView) findViewById(R.id.listView_technology);
        resultList_technology.setOnTouchListener(new ListView.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        // Disallow ScrollView to intercept touch events.
                        v.getParent().requestDisallowInterceptTouchEvent(true);
                        break;

                    case MotionEvent.ACTION_UP:
                        // Allow ScrollView to intercept touch events.
                        v.getParent().requestDisallowInterceptTouchEvent(false);
                        break;
                }

                // Handle ListView touch events.
                v.onTouchEvent(event);
                return true;
            }
        });

    }

    public void enteringData_technology(){
        for(int i=0; i < myResultList_technology.size();i++){
            HashMap<String, String> hashMap_technology = new HashMap<String, String>();
            hashMap_technology = myResultList_technology.get(i);
            dataList_technology.add(new ListItem_technology(hashMap_technology.get(TECH_USED)));
            adapter_technology = new CustomListAdapter_technology(this, R.layout.list_item_technology, dataList_technology);
            adapter_technology.notifyDataSetChanged();
            resultList_technology.setAdapter(adapter_technology);
        }
    }
    /**
     * Async task class to get json by making HTTP call
     * */
    private class GetMeasuredData_technology extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(MainActivity_technology.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            // Creating service handler class instance
            ServiceHandler_technology sh = new ServiceHandler_technology();
            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(url, ServiceHandler_alexa.GET);
            Log.d("Response: ", "> " + jsonStr);
            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);
                    // Getting JSON Array node
                    JSONObject jsonObj1= jsonObj.getJSONObject("data");
                    JSONObject jsonObj2= jsonObj1.getJSONObject("response");
                    JSONObject jsonObj3= jsonObj2.getJSONObject("data");
                    JSONObject jsonObj4= jsonObj3.getJSONObject("technology");
                    JSONObject jsonObj5= jsonObj4.getJSONObject("technology");

                    // looping through All result
                    ip_tech=jsonObj5.getString("ip");
                    city_tech=jsonObj5.getString("city");
                    region_tech=jsonObj5.getString("region");
                    country_tech=jsonObj5.getString("country");
                    hosting_provider = jsonObj5.getString("org");

                    Log.v("1", ip_tech);
                    Log.v("2", city_tech);
                    Log.v("3", region_tech);
                    Log.v("4", country_tech);
                    Log.v("5", hosting_provider);


                    if ((jsonObj5.optJSONArray("tech_used") == null) && (jsonObj5.optJSONObject("tech_used") == null)) {

                        HashMap<String, String> measuredResult_technology = new HashMap<String, String>();

                        measuredResult_technology.put(TECH_USED,"");
                        myResultList_technology.add(measuredResult_technology);

                    } else {
                        JSONArray jArray5 = jsonObj5.getJSONArray("tech_used");
                        for (int i = 0; i < jArray5.length(); i++) {


                            String value = jArray5.getString(i);
                            HashMap<String, String> measuredResult_technology = new HashMap<String, String>();

                            measuredResult_technology.put(TECH_USED,value);
                            myResultList_technology.add(measuredResult_technology);
                            

                        }
                    }



                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                Log.e("ServiceHandler", "Couldn't get any dat3a from the url");
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void none) {
             TextView ip=(TextView)findViewById(R.id.IP);
            TextView city=(TextView)findViewById(R.id.CITY);
            TextView region=(TextView)findViewById(R.id.REGION);
            TextView country=(TextView)findViewById(R.id.COUNTRY);
            TextView hosting =(TextView)findViewById(R.id.HOSTING_PROVIDER);
            ImageView country_image=(ImageView)findViewById(R.id.COUNTRY_IMAGE);
            String final1 =country_tech.toLowerCase();

            int identifier = getApplicationContext().getResources().getIdentifier(final1, "drawable", "com.localexample.website_analyser");
            country_image.setImageResource(identifier);
            ip.setText(ip_tech);
            city.setText(city_tech);
            region.setText("("+region_tech+")"+",");
            country.setText(country_tech);
            hosting.setText(hosting_provider);
            enteringData_technology();
            // Dismiss the progress dialog

            if (pDialog.isShowing())
                pDialog.dismiss();
        }

    }


}