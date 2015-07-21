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

public class MainActivity_alexa extends Activity {

    private ProgressDialog pDialog;
    List<ListItem_alexa> dataList_alexa;
    Handler handler;
    private ListView resultList_alexa;
    ArrayList<HashMap<String, String>> myResultList_alexa;
    CustomListAdapter_alexa adapter_alexa;
    // URL to get contacts JSON
    private static String url = "https://apiv2dev.rankwatch.com/wa/wadetails/json/";
    // JSON Node names
    int estt=0;
    long global_rank,global_change,country_rank;
    String b_rate="22";
    private static final double EST = 0;
    private static final double GLOBAL_RANK = 0;
    private static final double GLOBAL_CHANGE =0;
    private static final String BRATE = "inputText";
    private static final double COUNTRY_RANK = 0;
    private static final String COUNTRY_NAME = "i";
    private static final String COUNTRY_VALUE = "in";
    ArrayList<String> measureList;

    // contacts JSONArray
    JSONArray result = null;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_alexa);
        myResultList_alexa = new ArrayList<HashMap<String, String>>();


        new GetMeasuredData_alexa().execute();
        dataList_alexa = new ArrayList<>();
        resultList_alexa= (ListView) findViewById(R.id.listView_alexa);
        resultList_alexa.setOnTouchListener(new ListView.OnTouchListener() {
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

    public void enteringData_alexa(){
        for(int i=0; i < myResultList_alexa.size();i++){
            HashMap<String, String> hashMap_alexa = new HashMap<String, String>();
            hashMap_alexa = myResultList_alexa.get(i);
            dataList_alexa.add(new ListItem_alexa(hashMap_alexa.get(COUNTRY_NAME).toString(),hashMap_alexa.get(COUNTRY_VALUE).toString()));
            adapter_alexa = new CustomListAdapter_alexa(this, R.layout.list_item_alexa, dataList_alexa);
            adapter_alexa.notifyDataSetChanged();
            resultList_alexa.setAdapter(adapter_alexa);
        }
    }
    /**
     * Async task class to get json by making HTTP call
     * */
    private class GetMeasuredData_alexa extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(MainActivity_alexa.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            // Creating service handler class instance
            ServiceHandler_alexa sh = new ServiceHandler_alexa();
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
                    JSONObject jsonObj4= jsonObj3.getJSONObject("alexa");
                    JSONObject jsonObj5= jsonObj4.getJSONObject("alexa");

                    // looping through All result
estt=jsonObj5.getInt("est");
                    global_rank=jsonObj5.getLong("g_rank");
                    global_rank=jsonObj5.getLong("g_rank");
                    global_change=jsonObj5.getLong("g_change");
                    country_rank = jsonObj5.getLong("c_rank");
                    b_rate = jsonObj5.getString("b_rate");
                    Log.v("1", String.valueOf(estt));
                    Log.v("2", String.valueOf(global_rank));
                    Log.v("3", String.valueOf(global_change));
                    Log.v("4", b_rate);


                    if ((jsonObj5.optJSONArray("locations") == null) && (jsonObj5.optJSONObject("locations") == null)) {

                        HashMap<String, String> measuredResult_alexa = new HashMap<String, String>();
                        measuredResult_alexa.put(COUNTRY_NAME,"");
                        myResultList_alexa.add(measuredResult_alexa);

                    } else {
                        JSONArray jArray5 = jsonObj5.getJSONArray("locations");
                        for (int i = 0; i < jArray5.length(); i++) {

                            JSONObject jsonObject_alexa = jArray5.getJSONObject(i);
                            String country_name = jsonObject_alexa.getString("c_name");
                            String value = jsonObject_alexa.getString("value");

                            Log.v("SOURCE", country_name);
                            Log.v("val", value);
                            HashMap<String, String> measuredResult_alexa = new HashMap<String, String>();
                            measuredResult_alexa.put(COUNTRY_NAME,country_name);
                            measuredResult_alexa.put(COUNTRY_VALUE,value);

                            myResultList_alexa.add(measuredResult_alexa);
                            

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
            ProgressBar p1=(ProgressBar)findViewById(R.id.progressBar2);
            TextView gr=(TextView)findViewById(R.id.GLOBAL_RANK);
            TextView cr=(TextView)findViewById(R.id.COUNTRY_RANk);
            TextView br=(TextView)findViewById(R.id.BOUNCE_RATE);
            p1.setScrollBarStyle(ProgressBar.SCROLLBARS_OUTSIDE_INSET);
            p1.setVisibility(View.VISIBLE);
            p1.setProgress(0);
            p1.setMax(10);

            if(estt>=1 && estt<=3) {
                p1.incrementProgressBy(estt);
                p1.getProgressDrawable().setColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_IN);
            }
            else if(estt>3 && estt <=6)
            {
                p1.incrementProgressBy(estt);
                p1.getProgressDrawable().setColorFilter(Color.RED, PorterDuff.Mode.SRC_IN);
            }
            else
            {
                p1.incrementProgressBy(estt);
                p1.getProgressDrawable().setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_IN);

            }


            if (global_rank==0)
            {
             gr.setText("NO DATA aVALIABLE FOR YUR WEBSITE");
            }
            else
            gr.setText(global_rank+"^"+"th"+"("+global_change+")"+"most visited website in the World.");
            if (country_rank==0)
            {
                cr.setText("NO DATA aVALIABLE FOR YUR WEBSITE");
            }
            else
                cr.setText(country_rank+"^"+"most visited website in"+ COUNTRY_NAME);

            if (b_rate== "\n-              ")
            {
                br.setText("NO DATA aVALIABLE FOR YUR WEBSITE");
            }
            else
                br.setText("Estimated Bounce Rate is"+b_rate);


            enteringData_alexa();
            // Dismiss the progress dialog

            if (pDialog.isShowing())
                pDialog.dismiss();
        }

    }


}