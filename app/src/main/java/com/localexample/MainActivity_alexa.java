package com.localexample;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.localexample.website_analyser.R;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.SQLException;
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
    String countryname= "";
    long global_rank,global_change,country_rank;
    String b_rate="22";
    String country_name="sg";
    private static final double EST = 0;
    private static final double GLOBAL_RANK = 0;
    private static final double GLOBAL_CHANGE =0;
    private static final String BRATE = "inputText";
    private static final double COUNTRY_RANK = 0;
    private static final String COUNTRY_NAME = "i";
    private static final String COUNTRY_VALUE = "in";
    InputStream is = null;
    String result1 = null;
    ArrayList<String> measureList;
    SMSBlockerDataBaseAdapter alexa;
    // contacts JSONArray
    JSONArray result = null;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_alexa);
        myResultList_alexa = new ArrayList<HashMap<String, String>>();
        alexa=new SMSBlockerDataBaseAdapter(this);
        try {
            alexa=alexa.open();
            alexa.insertEntry("www.vocabmonk.com", "alexa", "3e418073e42fa953a2bf389e379b236b", 898);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String module=alexa.getSinlgeEntry("www.vocabmonk.com","alexa");
        Log.v("MODULE", module);

        if("alexa".equals(module))
        {
            Toast.makeText(MainActivity_alexa.this, "Congrats: ID AVAILABLE", Toast.LENGTH_LONG).show();

        }
        else
        {
            Toast.makeText(MainActivity_alexa.this, "ID NOT VAILABLE", Toast.LENGTH_LONG).show();
            //Creating service handler class instance
            Intent i =new Intent(getApplicationContext(),apitask_alexa.class);
            startActivity(i);
        }

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
            dataList_alexa.add(new ListItem_alexa(hashMap_alexa.get(COUNTRY_NAME).toString(),hashMap_alexa.get(COUNTRY_VALUE)));
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
            try {


                HttpEntity httpEntity = null;
                // HttpResponse httpResponse = null;
                DefaultHttpClient httpClient = new DefaultHttpClient();
                String username= "wa-v2-01-12345";
                String password ="123456789";
                String passwd =alexa.getSinlgeEntry2("www.vocabmonk.com","alexa");
                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
                nameValuePairs.add(new BasicNameValuePair("id",passwd));
                nameValuePairs.add(new BasicNameValuePair("callback", "WWW.vocabmonk.com"));
                String paramsString = URLEncodedUtils.format(nameValuePairs, "UTF-8");
                HttpGet httpGet = new HttpGet(url+"basic-auth/user/passwd"+"?"+paramsString);
                UsernamePasswordCredentials credentials = new UsernamePasswordCredentials(username,password);
                BasicScheme scheme = new BasicScheme();
                Header authorizationHeader = scheme.authenticate(credentials, httpGet);
                httpGet.addHeader(authorizationHeader);
                HttpResponse httpResponse = httpClient.execute(httpGet);
                // receive response as inputStream
                httpEntity = httpResponse.getEntity();
                is = httpEntity.getContent();
                alexa.close();
            } catch (Exception e) {
                Log.d("InputStream", e.getLocalizedMessage());
            }

            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(is, "utf-8"), 8);
                StringBuilder sb = new StringBuilder();
                String line = null;
                while ((line = reader.readLine()) != null) {
                    sb.append(line + "\n");
                    result1=result1+line;
                }
                result1 = sb.toString();
            } catch (Exception e) {
                return null;
            }

            Log.d("Response: ", "> " + result1);
            if (result1!= null) {
                try {
                    JSONObject jsonObj = new JSONObject(result1);
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
                   // Log.v("2", String.valueOf(global_rank));
                    //Log.v("3", String.valueOf(global_change));
                    //Log.v("4", b_rate);


                    if ((jsonObj5.optJSONArray("locations") == null) && (jsonObj5.optJSONObject("locations") == null)) {

                        HashMap<String, String> measuredResult_alexa = new HashMap<String, String>();
                        measuredResult_alexa.put(COUNTRY_NAME,"");
                        myResultList_alexa.add(measuredResult_alexa);

                    } else {
                        JSONArray jArray5 = jsonObj5.getJSONArray("locations");
                        for (int i = 0; i < jArray5.length(); i++) {

                            JSONObject jsonObject_alexa = jArray5.getJSONObject(i);
                            country_name = jsonObject_alexa.getString("c_name");
                            String value = jsonObject_alexa.getString("value");
                            countryname=country_name;

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
            enteringData_alexa();
            ProgressBar p1=(ProgressBar)findViewById(R.id.progressBar2);
            TextView gr=(TextView)findViewById(R.id.GLOBALRANK);
            TextView cr=(TextView)findViewById(R.id.COUNTRY_RANk);
            TextView cr_name=(TextView)findViewById(R.id.COUNTRYNAME);
            TextView crtext=(TextView)findViewById(R.id.COUNTRYTEXT6);
            TextView crpow=(TextView)findViewById(R.id.COUNTRYPOWER);
            TextView br=(TextView)findViewById(R.id.BOUNCE_RATE);
            p1.setScrollBarStyle(ProgressBar.SCROLLBARS_OUTSIDE_INSET);
            TextView power=(TextView)findViewById(R.id.POWER);
            TextView firstbr=(TextView)findViewById(R.id.FIRSTBRACKET);
            TextView change=(TextView)findViewById(R.id.CHANGE);
            TextView secondbr=(TextView)findViewById(R.id.SECONDBRACKET);
            ImageView changeimg=(ImageView)findViewById(R.id.BRACKETIMAGE);
            TextView quote=(TextView)findViewById(R.id.QUOTE);


            if (global_rank==0)
            {
             quote.setText("No Global Rank Available. ");
            }
            else {

                gr.setText(String.valueOf(global_rank));
                firstbr.setText("( ");
                power.setText("th");
                if(global_change<global_rank)
                {
                    changeimg.setImageResource(R.drawable.green_change);
                }
                else
                {
                 changeimg.setImageResource(R.drawable.change_red);
                }
                change.setText(String.valueOf(global_change));
                secondbr.setText(") ");
                quote.setText(" most visited website in the World. ");
            }

                if (country_rank==0)
            {
                cr.setText("No Country Rank Available.");
            }
            else {
                    cr.setText(String.valueOf(country_rank));
                    crpow.setText("th");
                    crtext.setText("most visited website in ");
                    Log.v("country", COUNTRY_NAME);
                    cr_name.setText(countryname + ".");
                }
            if (b_rate== "\n-")
            {
                br.setText("Not Available..");
            }
            else
                br.setText("Estimated Bounce Rate is "+ b_rate);

            p1.setVisibility(View.VISIBLE);
            p1.setProgress(0);
            p1.setMax(10);

            Resources res = getResources();
            Rect bounds = p1.getProgressDrawable().getBounds();
            if(estt>=1 && estt<=3) {
                p1.setProgress(0);
                p1.setMax(10);
                p1.setProgressDrawable(res.getDrawable(R.drawable.redprogressbar));
                //p1.incrementProgressBy(estt);
            }
            else if(estt>3 && estt <=6)
            {
                //p1.incrementProgressBy(estt);
                p1.setProgress(0);
                p1.setMax(10);
                p1.setProgressDrawable(res.getDrawable(R.drawable.orangeprogressbar));
            }
            else
            {
                // p1.incrementProgressBy(estt);
                p1.setProgress(0);
                p1.setMax(10);
                p1.setProgressDrawable(res.getDrawable(R.drawable.greenprogressbar));
            }

            p1.getProgressDrawable().setBounds(bounds);
            p1.setProgress(estt);
            ScrollView sv;
            sv=(ScrollView)findViewById(R.id.scrollView1);
            sv.fullScroll(ScrollView.FOCUS_UP);
            // Dismiss the progress dialog

            if (pDialog.isShowing())
                pDialog.dismiss();
        }

    }


}