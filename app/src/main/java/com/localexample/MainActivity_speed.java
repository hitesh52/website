package com.localexample;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.Highlight;
import com.github.mikephil.charting.utils.PercentFormatter;
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
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class MainActivity_speed extends Activity {
    InputStream is = null;
    String result1 = null;
    private ProgressDialog pDialog;
    List<ListItem_speed> dataList_speed;
    List<ListItem_speed_second> dataList_speed_second;
    List<ListItem_speed_tips> dataList_speed_tips;
    Handler handler;
    private LinearLayout linearLayout;
    private LinearLayout linearLayout2;
    private PieChart mchart;
    private float[] yData = {5, 10, 15, 30,20,60,20,1};
    SMSBlockerDataBaseAdapter speed;
    private String[] xData = {"DOC", "CSS", "FONT", "JS","CSSIMAGE","IMAGE","FAVICON","IFRAME"};
    private PieChart mchart2;
    private float[] yData2 = {5, 10, 15, 30,20,60,20,1};

    private String[] xData2 = {"DOC", "CSS", "FONT", "JS","CSSIMAGE","IMAGE","FAVICON","IFRAME"};
    int req_total = 0;
    double size_total = 0.0;
    int req_total_c = 0;
    double size_total_c = 0.0;
    String fin="";
    private ListView resultList_speed;
    private ListView resultList_speed_second;
    private ListView resultList_speed_tips;
    ArrayList<HashMap<String, String>> myResultList_speed;
    ArrayList<HashMap<String, String>> myResultList_speed_second;
    ArrayList<HashMap<String, String>> myResultList_speed_tips;
    CustomListAdapter_speed adapter_speed;
    CustomListAdapter_speed_second adapter_speed_second;
    CustomListAdapter_speed_tips adapter_speed_tips;
    // URL to get contacts JSON
    private static String url = "https://apiv2dev.rankwatch.com/wa/wadetails/json/";
    // JSON Node names
    float cached_page_size,total_page_size,page_load_time;
    Long total_requests,y_score;
    private static final String REQUEST = "w";
    private static final String SIZE = "s";
    private static final String REQUEST2 = "hryhrt";
    private static final String SIZE2 = "tyh";
    private static final String OBJ_NAME2 = "dddvs";
    private static final String OBJ_NAME = "ddds";
    private static final String URL = "wt";
    private static final String MESSAGE = "sh";
    private static final String SCORE = "hrfhyhrt";
    private static final String VALUE = "tcyh";
    ArrayList<String> measureList;

    // contacts JSONArray
    JSONArray result = null;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_speed);
        // Listview on item click listener
        myResultList_speed = new ArrayList<HashMap<String, String>>();
        myResultList_speed_second = new ArrayList<HashMap<String, String>>();
        myResultList_speed_tips = new ArrayList<HashMap<String, String>>();




        speed=new SMSBlockerDataBaseAdapter(this);
        try {
            speed=speed.open();
            speed.insertEntry("www.vocabmonk.com","speed","86cdd7a507a52c626866c209d13adf53",898);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String module=speed.getSinlgeEntry("www.vocabmonk.com","speed");
        Log.v("MODULE",module);

        if("speed".equals(module))
        {
            Toast.makeText(MainActivity_speed.this, "Congrats: ID AVAILABLE", Toast.LENGTH_LONG).show();

        }
        else
        {
            Toast.makeText(MainActivity_speed.this, "ID NOT VAILABLE", Toast.LENGTH_LONG).show();
            // Creating service handler class instance
             Intent i =new Intent(getApplicationContext(),apitask_speed.class);
             startActivity(i);


        }
        new GetMeasuredData_speed().execute();
        dataList_speed = new ArrayList<>();
        dataList_speed_second = new ArrayList<>();
        dataList_speed_tips = new ArrayList<>();
        linearLayout =(LinearLayout)findViewById(R.id.linearLayout78);
        linearLayout2 =(LinearLayout)findViewById(R.id.linearLayout81);
        resultList_speed = (ListView) findViewById(R.id.listView);
        resultList_speed_second = (ListView) findViewById(R.id.listView2);
        resultList_speed_tips = (ListView) findViewById(R.id.list22);
//
         resultList_speed.setOnTouchListener(new ListView.OnTouchListener() {
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

        resultList_speed_tips.setOnTouchListener(new ListView.OnTouchListener() {
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
        resultList_speed_second.setOnTouchListener(new ListView.OnTouchListener() {
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


        resultList_speed_tips.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // getting values from selected ListItem
                String name = ((TextView) view.findViewById(R.id.RECOMMENDATION))
                        .getText().toString();
              // String cost = ((TextView) view.findViewById(R.id.DATA))
                //       .getText().toString();
                String description = ((TextView) view.findViewById(R.id.PRIORITY))
                        .getText().toString();
                // Starting single contact activity
                Intent in = new Intent(getApplicationContext(),
                        SingleContactActivity.class);
                in.putExtra("TAG_NAME",name );
                in.putExtra("TAG_EMAIL",URL);
                in.putExtra("TAG_PHONE_MOBILE", description);
                startActivity(in);

            }
        });



    }


    public void enteringData_speed() {
        for (int i = 0; i < myResultList_speed.size(); i++) {
            HashMap<String, String> hashMap_speed = new HashMap<String, String>();
            hashMap_speed = myResultList_speed.get(i);
            dataList_speed.add(new ListItem_speed(hashMap_speed.get(REQUEST), hashMap_speed.get(SIZE), hashMap_speed.get(OBJ_NAME)));
            adapter_speed = new CustomListAdapter_speed(this, R.layout.list_item_timestats, dataList_speed);
            adapter_speed.notifyDataSetChanged();
            resultList_speed.setAdapter(adapter_speed);
        }

    }
    public void enteringData_speed_second() {
        for (int i = 0; i < myResultList_speed_second.size(); i++) {
            HashMap<String, String> hashMap_speed_second = new HashMap<String, String>();
            hashMap_speed_second = myResultList_speed_second.get(i);
            dataList_speed_second.add(new ListItem_speed_second(hashMap_speed_second.get(REQUEST2), hashMap_speed_second.get(SIZE2), hashMap_speed_second.get(OBJ_NAME2)));
            adapter_speed_second = new CustomListAdapter_speed_second(this, R.layout.list_item_timestats_second, dataList_speed_second);
            adapter_speed_second.notifyDataSetChanged();
            resultList_speed_second.setAdapter(adapter_speed_second);
        }
    }
    public void enteringData_speed_tips() {
        for (int i = 0; i < myResultList_speed_tips.size(); i++) {
            HashMap<String, String> hashMap_speed_tips = new HashMap<String, String>();
            hashMap_speed_tips = myResultList_speed_tips.get(i);
            dataList_speed_tips.add(new ListItem_speed_tips(hashMap_speed_tips.get(MESSAGE), hashMap_speed_tips.get(SCORE), hashMap_speed_tips.get(VALUE), hashMap_speed_tips.get(URL)));
            adapter_speed_tips = new CustomListAdapter_speed_tips(this, R.layout.list_item_speed_tips, dataList_speed_tips);
            adapter_speed_tips.notifyDataSetChanged();
            resultList_speed_tips.setAdapter(adapter_speed_tips);
        }

    }



    /**
     * Async task class to get json by making HTTP call
     */
    private class GetMeasuredData_speed extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
           // Showing progress dialog
            pDialog = new ProgressDialog(MainActivity_speed.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            try {

                String result = "";
                HttpEntity httpEntity = null;
                // HttpResponse httpResponse = null;
                DefaultHttpClient httpClient = new DefaultHttpClient();
                String username= "wa-v2-01-12345";
                String password ="123456789";

                String passwd = speed.getSinlgeEntry2("www.vocabmonk.com","speed");
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
                speed.close();
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
            if (result1 != null) {
                try {
                    JSONObject jsonObj = new JSONObject(result1);
                    // Getting JSON Array node
                    JSONObject jsonObj1 = jsonObj.getJSONObject("data").getJSONObject("response").getJSONObject("data").getJSONObject("yslow").getJSONObject("yslow");
                     float cached=jsonObj1.getLong("page_size_c");
                      cached_page_size=cached/1024;
                     total_requests=jsonObj1.getLong("requests");
                     float page_size1=jsonObj1.getLong("page_size");
                     total_page_size=page_size1/1024;
                    page_load_time=jsonObj1.getLong("load_time");
                    y_score=jsonObj1.getLong("y_score");
                    if ((jsonObj1.optJSONArray("stats") == null) && (jsonObj1.optJSONObject("stats") == null)) {

                        HashMap<String, String> measuredResult_content_breakdown = new HashMap<String, String>();
                        measuredResult_content_breakdown.put(REQUEST, "");
                        measuredResult_content_breakdown.put(SIZE, "");
                        myResultList_speed.add(measuredResult_content_breakdown);

                    } else {
                        JSONObject stats = jsonObj1.getJSONObject("stats");
                        Iterator x = stats.keys();
                        JSONArray jsonArray = new JSONArray();

                        while (x.hasNext()) {
                            String key = (String) x.next();
                            jsonArray.put(stats.get(key));
                        }
                        String[] a1 = new String[]{"doc", "css", "font", "js", "cssimage", "image", "favicon", "iframe"};
                        for (int i = 0; i < jsonArray.length(); i++) {
                            //    for (int j = 0; j < a1.length; j++) {
                            JSONObject jo = jsonArray.getJSONObject(i);
                            String obj_name = a1[i];
                            String re = jo.getString("r");
                            String we = jo.getString("w");
                            Float f = Float.parseFloat(we);
                            int ie = Integer.parseInt(re);
                            Float final2 = f / 1024;
                            size_total = size_total + round(final2, 2);
                            req_total = req_total + ie;
                            HashMap<String, String> measuredResult_content_breakdown = new HashMap<String, String>();
                            measuredResult_content_breakdown.put(OBJ_NAME, obj_name);
                            measuredResult_content_breakdown.put(REQUEST, re);
                            measuredResult_content_breakdown.put(SIZE, String.valueOf(round(final2, 2)));
                            myResultList_speed.add(measuredResult_content_breakdown);
                            Log.v("hietsh1", re);
                            Log.v("hietsh1", we);

//                            }

                        }


                    }

                    if ((jsonObj1.optJSONArray("stats_c") == null) && (jsonObj1.optJSONObject("stats_c") == null)) {

                        HashMap<String, String> measuredResult_content_breakdown = new HashMap<String, String>();
                        measuredResult_content_breakdown.put(REQUEST2, "");
                        measuredResult_content_breakdown.put(SIZE2, "");
                        myResultList_speed_second.add(measuredResult_content_breakdown);

                    } else {
                        JSONObject stats_c = jsonObj1.getJSONObject("stats_c");
                        Iterator x = stats_c.keys();
                        JSONArray jsonArray_c = new JSONArray();

                        while (x.hasNext()) {
                            String key = (String) x.next();
                            jsonArray_c.put(stats_c.get(key));
                        }
                        String[] a1_c = new String[]{"doc", "css", "font", "js", "cssimage", "image", "favicon", "iframe"};
                        for (int i = 0; i < jsonArray_c.length(); i++) {
                            //    for (int j = 0; j < a1.length; j++) {
                            JSONObject jo = jsonArray_c.getJSONObject(i);
                            String obj_name_c = a1_c[i];
                            String re_c = jo.getString("r");
                            String we_c = jo.getString("w");
                            Float f_c = Float.parseFloat(we_c);
                            int ie_c= Integer.parseInt(re_c);
                            Float final2_c = f_c / 1024;
                            size_total_c = size_total_c + round(final2_c, 2);
                            req_total_c = req_total_c + ie_c;
                            HashMap<String, String> measuredResult_content_breakdown = new HashMap<String, String>();
                            measuredResult_content_breakdown.put(OBJ_NAME2, obj_name_c);
                            measuredResult_content_breakdown.put(REQUEST2, re_c);
                            measuredResult_content_breakdown.put(SIZE2, String.valueOf(round(final2_c, 2)));
                            myResultList_speed_second.add(measuredResult_content_breakdown);
                            Log.v("hietsh1", re_c);
                            Log.v("hietsh1", we_c);

                        }
                    }



                    JSONObject jsonObjtips = jsonObj.getJSONObject("data").getJSONObject("response").getJSONObject("data").getJSONObject("g_speed").getJSONObject("g_speed");

                    if ((jsonObjtips.optJSONArray("tips") == null) && (jsonObjtips.optJSONObject("tips") == null)) {

                        HashMap<String, String> measuredResult_tips = new HashMap<String, String>();
                        measuredResult_tips.put(MESSAGE, "");
                        measuredResult_tips.put(SCORE, "");
                        measuredResult_tips.put(VALUE, "");
                        myResultList_speed_tips.add(measuredResult_tips);

                    }else {
                        JSONArray tips_array = jsonObjtips.getJSONArray("tips");

                        for (int i = 0; i < tips_array.length();   i++) {
                            JSONObject int_job = tips_array.getJSONObject(i);
                            String message = int_job.getString("message");
                            int score=int_job.getInt("score");
                            String value =int_job.getString("value");
                            HashMap<String, String> measuredResult_tips = new HashMap<String, String>();
                            //
                            if ((int_job.optJSONArray("data") == null) && (int_job.optJSONObject("data") == null))
                            {
                                measuredResult_tips.put(MESSAGE, message);
                                measuredResult_tips.put(SCORE, String.valueOf(score));
                                measuredResult_tips.put(VALUE, value);

                                measuredResult_tips.put(URL,"");
                            }
                            else
                            {
                                JSONArray jarray=int_job.getJSONArray("data");
                                for(int j=0;j<=jarray.length()-10;j++)
                                {
                                    JSONObject obj_data = jarray.getJSONObject(j);
                                    String ur=obj_data.getString("URL");
                                     fin = fin + ur;
                                    // fin =fin+ "|||";
                                    measuredResult_tips.put(URL,fin);
                                    measuredResult_tips.put(MESSAGE, message);
                                    measuredResult_tips.put(SCORE, String.valueOf(score));
                                    measuredResult_tips.put(VALUE, value);
                                    myResultList_speed_tips.add(measuredResult_tips);
                                    Log.v("URLLLL",ur);

                                }






                            }






                            //

                        }
                    }

                    JSONObject jsonObjtips_yslow = jsonObj.getJSONObject("data").getJSONObject("response").getJSONObject("data").getJSONObject("yslow").getJSONObject("yslow");
                    if ((jsonObjtips.optJSONArray("tips") == null) && (jsonObjtips.optJSONObject("tips") == null)) {

                        HashMap<String, String> measuredResult_tips = new HashMap<String, String>();
                        measuredResult_tips.put(MESSAGE, "");
                        measuredResult_tips.put(SCORE, "");
                        measuredResult_tips.put(VALUE, "");
                        myResultList_speed_tips.add(measuredResult_tips);

                    }else {
                        JSONArray tips_array_yslow = jsonObjtips_yslow.getJSONArray("tips");

                        for (int i = 0; i < tips_array_yslow.length();   i++) {
                            JSONObject int_job_yslow = tips_array_yslow.getJSONObject(i);
                            String message = int_job_yslow.getString("message");
                            int score=int_job_yslow.getInt("score");
                            String value =int_job_yslow.getString("value");
                            HashMap<String, String> measuredResult_tips_yslow = new HashMap<String, String>();
                            measuredResult_tips_yslow.put(MESSAGE, message);
                            measuredResult_tips_yslow.put(SCORE, String.valueOf(score));
                            measuredResult_tips_yslow.put(VALUE, value);
                            //
                            if ((int_job_yslow.optJSONArray("data") == null) && (int_job_yslow.optJSONObject("data") == null))
                            {
                                measuredResult_tips_yslow.put(URL,"");
                            }
                            else
                            {
                                JSONArray jarray=int_job_yslow.getJSONArray("data");
                                for(int j=0;j<=jarray.length()-10;j++)
                                {
                                    JSONObject obj_data = jarray.getJSONObject(j);
                                    String ur=obj_data.getString("URL");
                                    fin = fin + ur;
                                   // fin =fin+ "|||";
                                }
                                measuredResult_tips_yslow.put(URL,fin);


                            }



                            myResultList_speed_tips.add(measuredResult_tips_yslow);


                            //

                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                Log.e("ServiceHandler", "Couldn't get any data from the url");
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void none) {
            enteringData_speed();
            enteringData_speed_second();
            enteringData_speed_tips();
            // Dismiss the progress dialog
            Log.v("hietsh5", String.valueOf(size_total));
            Log.v("hietsh6000", String.valueOf(req_total));
            TextView pageloadtime=(TextView)findViewById(R.id.PAGELOADTIME);
            TextView totalpagesize=(TextView)findViewById(R.id.TOTALPAGESIZE);
            TextView totalrequests =(TextView)findViewById(R.id.TOTALREQUESTS);
            TextView cachedpagesize=(TextView)findViewById(R.id.CACHEDPAGESIZE);
            TextView score=(TextView)findViewById(R.id.SCORE);
            TextView grade=(TextView)findViewById(R.id.GRADE);
            TextView req = (TextView) findViewById(R.id.REQUEST_TOTAL);
            TextView size = (TextView) findViewById(R.id.REQUEST_SIZE);
            TextView req_2 = (TextView) findViewById(R.id.REQUEST_TOTAL_2);
            TextView size_2 = (TextView) findViewById(R.id.REQUEST_SIZE_2);
            req.setText(String.valueOf(req_total));
            size.setText(String.valueOf(round((float) size_total, 2)));
            req_2.setText(String.valueOf(req_total_c));
            size_2.setText(String.valueOf(round((float) size_total_c, 2)));
            //size.setText((int) size_total);
            if(String.valueOf(page_load_time)!="")
            {
                pageloadtime.setText(String.valueOf(page_load_time));
            }
            if(String.valueOf(total_page_size)!="")
            {
                totalpagesize.setText(String.valueOf(total_page_size)+" kb");
            }
            if(String.valueOf(total_requests)!="")
            {
                totalrequests.setText(String.valueOf(total_requests));
            }
            if(String.valueOf(cached_page_size)!="")
            {
                cachedpagesize.setText(String.valueOf(cached_page_size)+" kb");
            }
            if(String.valueOf(y_score)!="")
            {
                score.setText(String.valueOf(y_score));
            }
            mchart = new PieChart(getApplicationContext());
            linearLayout.addView(mchart);
            linearLayout.setBackgroundColor(Color.LTGRAY);
            mchart.setUsePercentValues(true);
            mchart.setDescription("1ST TIME\n" + "STATS");
            mchart.setDrawHoleEnabled(true);
            mchart.setHoleColorTransparent(true);
            mchart.setHoleRadius(40);
            mchart.setTransparentCircleRadius(10);
            mchart.setRotationAngle(0);
            mchart.setRotationEnabled(true);
            mchart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
                @Override
                public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {
                    if (e == null) {
                        return;
                    }
                }

                @Override
                public void onNothingSelected() {

                }
            });
            addData();
            Legend l = mchart.getLegend();
            l.setPosition(Legend.LegendPosition.RIGHT_OF_CHART);
            l.setXEntrySpace(7);
            l.setXEntrySpace(5);
            mchart2 = new PieChart(getApplicationContext());
            linearLayout2.addView(mchart2);
            linearLayout2.setBackgroundColor(Color.LTGRAY);
            mchart2.setUsePercentValues(true);
            mchart2.setDescription("CACHED\n" +
                    "STATS");
            mchart2.setDrawHoleEnabled(true);
            mchart2.setHoleColorTransparent(true);
            mchart2.setHoleRadius(40);
            mchart2.setTransparentCircleRadius(10);
            mchart2.setRotationAngle(0);
            mchart2.setRotationEnabled(true);
            mchart2.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
                @Override
                public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {
                    if (e == null) {
                        return;
                    }
                }

                @Override
                public void onNothingSelected() {

                }
            });
            addData2();
            Legend l2 = mchart2.getLegend();
            l2.setPosition(Legend.LegendPosition.RIGHT_OF_CHART);
            l2.setXEntrySpace(7);
            l2.setXEntrySpace(5);


           if (pDialog.isShowing())
                pDialog.dismiss();
            ScrollView sv;
            sv=(ScrollView)findViewById(R.id.scrollView_speed);
            sv.fullScroll(ScrollView.FOCUS_UP);

        }
    }

    private void addData2() {
        ArrayList<Entry> yvals12=new ArrayList<Entry>();
        for(int i=0;i<yData2.length;i++) yvals12.add(new Entry(yData2[i],i));

        ArrayList<String> xVals2=new ArrayList<String>();
        for(int  i=0;i<xData2.length;i++)
            xVals2.add(xData2[i]);

        //creating Pie data sets
        PieDataSet dataSet2= new PieDataSet(yvals12,"CACHED\n" +
                "STATS");
        dataSet2.setSliceSpace(3);
        dataSet2.setSelectionShift(5);
        //Adding colours

        ArrayList<Integer> colours2=new ArrayList<Integer>();




        for(int c : ColorTemplate.VORDIPLOM_COLORS)
            colours2.add(c);
        for(int c : ColorTemplate.JOYFUL_COLORS)
            colours2.add(c);
        for(int c : ColorTemplate.COLORFUL_COLORS)
            colours2.add(c);
        for(int c : ColorTemplate.LIBERTY_COLORS)
            colours2.add(c);
        for(int c : ColorTemplate.PASTEL_COLORS)
            colours2.add(c);
        colours2.add(ColorTemplate.getHoloBlue());
        dataSet2.setColors(colours2);
        PieData data2 =new PieData(xVals2,dataSet2);
        data2.setValueFormatter(new PercentFormatter());
        data2.setValueTextSize(11f);
        data2.setValueTextColor(Color.GRAY);
        mchart2.setData(data2);
        mchart2.highlightValues(null);
        mchart2.invalidate();

    }


    private void addData() {
        ArrayList<Entry> yvals1=new ArrayList<Entry>();
        for(int i=0;i<yData.length;i++) yvals1.add(new Entry(yData[i],i));

        ArrayList<String> xVals=new ArrayList<String>();
        for(int  i=0;i<xData.length;i++)
            xVals.add(xData[i]);

        //creating Pie data sets
        PieDataSet dataSet= new PieDataSet(yvals1,"1ST TIME\n" +
                "STATS\n");
        dataSet.setSliceSpace(3);
        dataSet.setSelectionShift(5);
        //Adding colours

        ArrayList<Integer> colours=new ArrayList<Integer>();




        for(int c : ColorTemplate.VORDIPLOM_COLORS)
            colours.add(c);
        for(int c : ColorTemplate.JOYFUL_COLORS)
            colours.add(c);
        for(int c : ColorTemplate.COLORFUL_COLORS)
            colours.add(c);
        for(int c : ColorTemplate.LIBERTY_COLORS)
            colours.add(c);
        for(int c : ColorTemplate.PASTEL_COLORS)
            colours.add(c);
        colours.add(ColorTemplate.getHoloBlue());
        dataSet.setColors(colours);
        PieData data =new PieData(xVals,dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(11f);
        data.setValueTextColor(Color.GRAY);
        mchart.setData(data);
        mchart.highlightValues(null);
        mchart.invalidate();
    }



    public static float round(float d, int decimalPlace) {
        return BigDecimal.valueOf(d).setScale(decimalPlace, BigDecimal.ROUND_HALF_UP).floatValue();
    }

}