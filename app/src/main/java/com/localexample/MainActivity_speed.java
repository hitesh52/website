package com.localexample;

import android.app.Activity;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import static java.lang.Math.round;

public class MainActivity_speed extends Activity {

    private ProgressDialog pDialog;
    List<ListItem_speed> dataList_speed;
    List<ListItem_speed_second> dataList_speed_second;
    List<ListItem_speed_tips> dataList_speed_tips;
    Handler handler;
    private LinearLayout linearLayout;
    private LinearLayout linearLayout2;
    private PieChart mchart;
    private float[] yData = {5, 10, 15, 30,20,60,20,1};

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
                String cost = ((TextView) view.findViewById(R.id.DATA))
                        .getText().toString();
                String description = ((TextView) view.findViewById(R.id.PRIORITY))
                        .getText().toString();
                // Starting single contact activity
                Intent in = new Intent(getApplicationContext(),
                        SingleContactActivity.class);
                in.putExtra("TAG_NAME",name );
                in.putExtra("TAG_EMAIL",cost);
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
            // Creating service handler class instance
            ServiceHandler_speed sh = new ServiceHandler_speed();
            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(url, ServiceHandler_speed.GET);
            Log.d("Response: ", "> " + jsonStr);
            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);
                    // Getting JSON Array node
                    JSONObject jsonObj1 = jsonObj.getJSONObject("data").getJSONObject("response").getJSONObject("data").getJSONObject("yslow").getJSONObject("yslow");

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
                            measuredResult_tips.put(MESSAGE, message);
                            measuredResult_tips.put(SCORE, String.valueOf(score));
                            measuredResult_tips.put(VALUE, value);
                            //
                            if ((int_job.optJSONArray("data") == null) && (int_job.optJSONObject("data") == null))
                            {
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
                                     fin =fin+ "|||";
                                }

                                measuredResult_tips.put(URL,fin);


                            }



                            myResultList_speed_tips.add(measuredResult_tips);


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

            TextView req = (TextView) findViewById(R.id.REQUEST_TOTAL);
            TextView size = (TextView) findViewById(R.id.REQUEST_SIZE);
            TextView req_2 = (TextView) findViewById(R.id.REQUEST_TOTAL_2);
            TextView size_2 = (TextView) findViewById(R.id.REQUEST_SIZE_2);
            req.setText(String.valueOf(req_total));
            size.setText(String.valueOf(round((float) size_total, 2)));
            req_2.setText(String.valueOf(req_total_c));
            size_2.setText(String.valueOf(round((float) size_total_c, 2)));
            //size.setText((int) size_total);

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
            mchart2.setDescription("1ST TIME\n" + "STATS");
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
        PieDataSet dataSet= new PieDataSet(yvals1,"LINKS AVAILABLE");
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