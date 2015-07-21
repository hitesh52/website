package com.localexample;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity_seo extends Activity {



    private LinearLayout linearLayout;
    private PieChart mchart;
    private float[] yData ={5,10,15,30};

    private  String[] xData={"INTERNAL FOLLOW","INTERNAL DON'T FOLLOW","EXTERNAL FOLLOW","EXTERNAL DONT FOLLOW"};
float external_link_follow_count=0;
    float external_link_no_follow_count=0;
    float internal_link_follow_count=0;
    int tot=0;    float internal_link_no_follow_count=0;
   private ProgressDialog pDialog;
    List<ListItem> dataList;
    List<ListItem_image> dataList_image;
    List<ListItem_kw_name> dataList_kw_name;
    List<ListItem_kw_two_name> dataList_kw_two_name;
    List<ListItem_kw_two_two_name> dataList_kw_two_two_name;
    ArrayList<ListItem_kw_three_name> dataList_kw_three_name;

    Handler handler;
    private ListView ResultList_image;
    private ListView ResultList_kw_two_name;
    private ListView ResultList_kw_three_name;
    private ListView ResultList_kw_two_two_name;
    private ListView resultList;
    private ListView ResultList_kw_name;
    ArrayList<HashMap<String, String>> myResultList_kw_two_two_cloud_name;
    ArrayList<HashMap<String, String>> myResultList;
    ArrayList<HashMap<String, String>> myResultList_image;
    ArrayList<HashMap<String, String>> myResultList_kw_cloud_name;
    ArrayList<HashMap<String, String>> myResultList_kw_two_cloud_name;
    ArrayList<HashMap<String, String>> myResultList_kw_three_cloud_name;
    CustomListAdapter adapter;
    CustomListAdapter_image adapter_image;
    CustomListAdapter_kw_name adapter_kw_name;
    CustomListAdapter_kw_two_name adapter_kw_two_name;

    CustomListAdapter_kw_two_two_name adapter_kw_two_two_name;
    CustomListAdapter_kw_three_name adapter_kw_three_name;
    // URL to get contacts JSON
    private static String url = "https://apiv2dev.rankwatch.com/wa/wadetails/json/";
    // JSON Node names
  String[] source;
    private static final String WWW_RESOLVED = "non_www";
    private static final String TAG_REWRITE = "url_rewrite";
    private static final String COUNTER = "h";
    private static final String FREQ = "y";
    private static final String FREQ_THREE = "yju";
    private static final String FREQ_TWO = "ydgd";
    private static final String TAG_KW_NAME="n";
    private static final String TAG_KW_TWO_NAME="t";
    private static final String TAG_KW_TWO_TITLE="u";
    private static final String TAG_KW_TWO_MDESC="m";
    private static final String TAG_KW_TWO_MKWS="k";
    private static final String TAG_KW_THREE_BODY="jz";
    private static final String TAG_KW_THREE_NAME="tz";
    private static final String TAG_KW_THREE_TITLE="uz";
    private static final String TAG_KW_THREE_MDESC="mz";
    private static final String TAG_KW_THREE_MKWS="kz";
    private static final String TAG_KW_TWO_BODY="jz";
    private static final String TAG_KW_TWO_TWO_NAME="tt";
    private static final String TAG_KW_TWO_TWO_TITLE="ut";
    private static final String TAG_KW_TWO_TWO_MDESC="mt";
    private static final String TAG_KW_TWO_TWO_MKWS="kt";
    private static final String TAG_KW_TWO_TWO_BODY="jt";
    private static final String TAG_KW_TITLE="z";
    private static final String TAG_IP = "ip_canonicalization";
    private static final String TAG_ROBOTS = "robots";
    private static final String TAG_SITEMAP = "sitemap";
    private static final String IMAGE = "img";
    private static final int HELLO1=0;
    private static final int HELLO2=0;
    private static final int HELLO3=0;
    String _favi="";
    String ext_follow="";
    int ext=0;
    int ext_n=0;
    int  in=0;
    int in_n=0;
    String ext_no_follow="";
    String freq="";
    String int_follow="";
    String int_no_follow="";
    private static final int HELLO4=0;
    private static final String TAG_TITLE = "title";
    String www_resolve,rewrite,ip,robots,sitemap,title_content,meta_desc_content,meta_desc_length,meta_kws_content,meta_kws_length,text_to_html,flash_count,i_frame,Print_css,
    w3_validator,page_rank, printcss , open_graph;
    private static final String TAG_DESC = "meta_desc";
    private static final String TAG_KWS = "meta_kws";
    ArrayList<HashMap<String, String>> arrayList;
    ArrayList<HashMap<String,String>> arrayList2;
    ArrayList<HashMap<String, String>> arrayList3;
    ArrayList<String> measureList;

    // contacts JSONArray
    JSONArray result = null;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        linearLayout=(LinearLayout)findViewById(R.id.linearLayout34);

        new GetMeasuredData().execute();
        myResultList = new ArrayList<HashMap<String, String>>();
        myResultList_image = new ArrayList<HashMap<String, String>>();
        myResultList_kw_cloud_name=new ArrayList<HashMap<String, String>>();
        myResultList_kw_two_cloud_name=new ArrayList<HashMap<String,String>>();
        myResultList_kw_two_two_cloud_name=new ArrayList<HashMap<String,String>>();
        myResultList_kw_three_cloud_name=new ArrayList<HashMap<String,String>>();

        //dataList_kw_name=new ArrayList<ListItem_kw_name>();
        dataList = new ArrayList<ListItem>();
        dataList_image = new ArrayList<ListItem_image>();
        dataList_kw_name=new ArrayList<ListItem_kw_name>();
        dataList_kw_two_name=new ArrayList<ListItem_kw_two_name>();
        dataList_kw_three_name=new ArrayList<ListItem_kw_three_name>();
        dataList_kw_two_two_name=new ArrayList<>();
        resultList= (ListView) findViewById(R.id.listing);
         ResultList_image=(ListView)findViewById(R.id.list_images);
        ResultList_kw_name=(ListView)findViewById(R.id.list_kw_name);
        ResultList_kw_two_name=(ListView)findViewById(R.id.listview_keyword_cloud);
        ResultList_kw_two_two_name=(ListView)findViewById(R.id.listView_kw_two_two);
        ResultList_kw_three_name=(ListView)findViewById(R.id.listView_three_keywords);
        resultList.setOnTouchListener(new ListView.OnTouchListener() {
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

        ResultList_image.setOnTouchListener(new ListView.OnTouchListener() {
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
        ResultList_kw_name.setOnTouchListener(new ListView.OnTouchListener() {
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
        ResultList_kw_two_name.setOnTouchListener(new ListView.OnTouchListener() {
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
        ResultList_kw_two_two_name.setOnTouchListener(new ListView.OnTouchListener() {
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
        ResultList_kw_three_name.setOnTouchListener(new ListView.OnTouchListener() {
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

    public void enteringData(){
        for(int i=0; i < myResultList.size();i++){
            HashMap<String, String> hashMap = new HashMap<String, String>();
            hashMap = myResultList.get(i);
            dataList.add(new ListItem(hashMap.get(TAG_REWRITE).toString(),hashMap.get(COUNTER).toString()));
            adapter = new CustomListAdapter(this, R.layout.list_item, dataList);
            adapter.notifyDataSetChanged();
            resultList.setAdapter(adapter);
        }

    }
    public void enteringData_image(){
        for(int i=0; i < myResultList_image.size();i++){
            HashMap<String, String> hashMap_image = new HashMap<String, String>();
            hashMap_image = myResultList_image.get(i);
            dataList_image.add(new ListItem_image(hashMap_image.get(IMAGE).toString()));
            adapter_image = new CustomListAdapter_image(this, R.layout.list_item_image, dataList_image);
            adapter_image.notifyDataSetChanged();
            ResultList_image.setAdapter(adapter_image);
        }

    }
    public void enteringData_kw_name(){
        for(int i=0; i <myResultList_kw_cloud_name.size();i++){
            HashMap<String, String> hashMap_kw_cloud = new HashMap<String, String>();
            hashMap_kw_cloud = myResultList_kw_cloud_name.get(i);
            dataList_kw_name.add(new ListItem_kw_name(hashMap_kw_cloud.get(TAG_KW_NAME),hashMap_kw_cloud.get(TAG_KW_TITLE)));
            adapter_kw_name = new CustomListAdapter_kw_name(this, R.layout.list_item_keyword_cloud, dataList_kw_name);
            adapter_kw_name.notifyDataSetChanged();
            ResultList_kw_name.setAdapter(adapter_kw_name);
        }

    }
    public void enteringData_kw_two_cloud(){
        for(int i=0; i < myResultList_kw_two_cloud_name.size();i++){
            HashMap<String, String> hashMap_kw_two_cloud = new HashMap<String, String>();
            hashMap_kw_two_cloud = myResultList_kw_two_cloud_name.get(i);
            dataList_kw_two_name.add(new ListItem_kw_two_name(hashMap_kw_two_cloud.get(TAG_KW_TWO_NAME),hashMap_kw_two_cloud.get(TAG_KW_TWO_TITLE),hashMap_kw_two_cloud.get(TAG_KW_TWO_MDESC),hashMap_kw_two_cloud.get(TAG_KW_TWO_BODY),hashMap_kw_two_cloud.get(FREQ)));
            adapter_kw_two_name = new CustomListAdapter_kw_two_name(this, R.layout.kw_cloud_two_word, dataList_kw_two_name);
            adapter_kw_two_name.notifyDataSetChanged();
            ResultList_kw_two_name.setAdapter(adapter_kw_two_name);
        }

    }
    public void enteringData_kw_two_two_cloud(){
        for(int i=0; i < myResultList_kw_two_two_cloud_name.size();i++){
            HashMap<String, String> hashMap_kw_two_two_cloud = new HashMap<String, String>();
            hashMap_kw_two_two_cloud = myResultList_kw_two_two_cloud_name.get(i);
            dataList_kw_two_two_name.add(new ListItem_kw_two_two_name(hashMap_kw_two_two_cloud.get(TAG_KW_TWO_TWO_NAME),hashMap_kw_two_two_cloud.get(TAG_KW_TWO_TWO_TITLE),hashMap_kw_two_two_cloud.get(TAG_KW_TWO_TWO_MDESC),hashMap_kw_two_two_cloud.get(TAG_KW_TWO_TWO_BODY),hashMap_kw_two_two_cloud.get(FREQ_TWO)));
            adapter_kw_two_two_name = new CustomListAdapter_kw_two_two_name(this, R.layout.list_item_key_two_two, dataList_kw_two_two_name);
            adapter_kw_two_two_name.notifyDataSetChanged();
            ResultList_kw_two_two_name.setAdapter(adapter_kw_two_two_name);
        }

    }
    public void enteringData_kw_three_cloud(){
        for(int i=0; i < myResultList_kw_three_cloud_name.size();i++){
            HashMap<String, String> hashMap_kw_three_cloud = new HashMap<String, String>();
            hashMap_kw_three_cloud = myResultList_kw_three_cloud_name.get(i);
            dataList_kw_three_name.add(new ListItem_kw_three_name(hashMap_kw_three_cloud.get(TAG_KW_THREE_NAME),hashMap_kw_three_cloud.get(TAG_KW_THREE_TITLE),hashMap_kw_three_cloud.get(TAG_KW_THREE_MDESC),hashMap_kw_three_cloud.get(TAG_KW_THREE_BODY),hashMap_kw_three_cloud.get(FREQ_THREE)));
            adapter_kw_three_name = new CustomListAdapter_kw_three_name(this, R.layout.kw_cloud_three_word, dataList_kw_three_name);
            adapter_kw_three_name.notifyDataSetChanged();
            ResultList_kw_three_name.setAdapter(adapter_kw_three_name);
        }

    }

    /**
     * Async task class to get json by making HTTP call
     * */
    private class GetMeasuredData extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(MainActivity_seo.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            // Creating service handler class instance
            ServiceHandler sh = new ServiceHandler();
            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(url, ServiceHandler.GET);
            Log.d("Response: ", "> " + jsonStr);
            if (jsonStr != null) {
                try {


                    // Getting JSON Array node
                    // looping through All result
                    JSONObject json = new JSONObject(jsonStr);    // create JSON obj from string
                    JSONObject json2 = json.getJSONObject("data");
                    JSONObject jsonResult = json2.getJSONObject("response");
                    JSONObject jsonResult1 = jsonResult.getJSONObject("data");
                    JSONObject jsonResult2 = jsonResult1.getJSONObject("seo");
                    JSONObject jsonResult3 = jsonResult2.getJSONObject("seo");
                    JSONObject jsonResult4 = jsonResult3.getJSONObject("meta_desc");
                    JSONObject jsonResult5 = jsonResult3.getJSONObject("meta_kws");
                    JSONObject jsonResult6 = jsonResult3.getJSONObject("title");
                    String view_port = jsonResult3.getString("viewport");
                    String kws = jsonResult3.getString(TAG_KWS);
                    JSONArray jsonMainArr = jsonResult3.getJSONArray("h1");
                    String www_resolved = jsonResult3.getString("non_www");


                    www_resolve = jsonResult3.getString(WWW_RESOLVED);
                    rewrite = jsonResult3.getString(TAG_REWRITE);
                    ip = jsonResult3.getString(TAG_IP);
                    robots = jsonResult3.getString(TAG_ROBOTS);
                    sitemap = jsonResult3.getString(TAG_SITEMAP);
                    title_content = jsonResult6.getString("content");
                    String title_length = jsonResult6.getString("length");
                    meta_desc_content = jsonResult4.getString("content");
                    meta_desc_length = jsonResult4.getString("length");
                    meta_kws_content = jsonResult5.getString("content");
                    meta_kws_length = jsonResult5.getString("count");

//HEADING TAGS
                    String h1 = "";
                    String hitesh2 = "";
                    String hitesh3 = "";
                    String hitesh4 = "";
                    String hitesh5 = null;

                    String cloud_name = "";
                    String two_cloud_name = "";
                    String three_cloud_name = "";


                    //  JSONArray jArray4 = jsonResult3.getJSONArray("h4");
//HEADING_TAG_H1


                    if ((jsonResult3.optJSONArray("h1") == null) && (jsonResult3.optJSONObject("h1") == null)) {

                        String hello = "NA";
                        Log.v("hello1001", hello);

                    } else {
                        JSONArray jArray1 = jsonResult3.getJSONArray("h1");
                        for (int i = 0; i < jArray1.length(); i++) {
                            String value = jArray1.getString(i);
                            HashMap<String, String> measuredResult = new HashMap<String, String>();
                            measuredResult.put(COUNTER, "H1");
                            measuredResult.put(TAG_REWRITE, value);
                            myResultList.add(measuredResult);

                        }
                    }

                    if ((jsonResult3.optJSONArray("h2") == null) && (jsonResult3.optJSONObject("h2") == null)) {

                        hitesh2 = "0";
                        Log.v("hello1002", hitesh2);

                    } else {

                        JSONArray jArray2 = jsonResult3.getJSONArray("h2");
                        for (int i = 0; i < jArray2.length(); i++) {
                            String value2 = jArray2.getString(i);
                            HashMap<String, String> measuredResult2 = new HashMap<String, String>();
                            measuredResult2.put(COUNTER, "H2");
                            measuredResult2.put(TAG_REWRITE, value2);
                            myResultList.add(measuredResult2);
                        }
                    }

                    if ((jsonResult3.optJSONArray("h3") == null) && (jsonResult3.optJSONObject("h3") == null)) {

                        hitesh3 = "0";
                        Log.v("hello1003", hitesh3);

                    } else {
                        JSONArray jArray3 = jsonResult3.getJSONArray("h3");
                        for (int i = 0; i < jArray3.length(); i++) {
                            String value3 = jArray3.getString(i);
                            HashMap<String, String> measuredResult3 = new HashMap<String, String>();
                            measuredResult3.put(COUNTER, "H3");
                            measuredResult3.put(TAG_REWRITE, value3);
                            myResultList.add(measuredResult3);
                        }
                    }
                    if ((jsonResult3.optJSONArray("h4") == null) && (jsonResult3.optJSONObject("h4") == null)) {

                        HashMap<String, String> measuredResult4 = new HashMap<String, String>();
                        measuredResult4.put(COUNTER, "H4");
                        measuredResult4.put(TAG_REWRITE, "No VALUE");
                        myResultList.add(measuredResult4);

                    } else {
                        JSONArray jArray4 = jsonResult3.getJSONArray("h4");
                        for (int i = 0; i < jArray4.length(); i++) {
                            String value4 = jArray4.getString(i);
                            HashMap<String, String> measuredResult4 = new HashMap<String, String>();
                            measuredResult4.put(COUNTER, "H4");
                            measuredResult4.put(TAG_REWRITE, value4);
                            myResultList.add(measuredResult4);
                        }
                    }

                    if ((jsonResult3.optJSONArray("h5") == null) && (jsonResult3.optJSONObject("h5") == null)) {

                        HashMap<String, String> measuredResult5 = new HashMap<String, String>();
                        measuredResult5.put(COUNTER, "H5");
                        measuredResult5.put(TAG_REWRITE, "No VALUE");
                        myResultList.add(measuredResult5);

                    } else {
                        JSONArray jArray5 = jsonResult3.getJSONArray("h5");
                        for (int i = 0; i < jArray5.length(); i++) {
                            String value5 = jArray5.getString(i);
                            HashMap<String, String> measuredResult5 = new HashMap<String, String>();
                            measuredResult5.put(COUNTER, "H4");
                            measuredResult5.put(TAG_REWRITE, value5);
                            myResultList.add(measuredResult5);

                        }
                    }
//IMAGE
                    if ((jsonResult3.optJSONArray("img") == null) && (jsonResult3.optJSONObject("img") == null)) {

                        HashMap<String, String> measuredResult_image_src = new HashMap<String, String>();
                        measuredResult_image_src.put(IMAGE, "NO IMAGES");
                        myResultList_image.add(measuredResult_image_src);

                    } else {
                        JSONObject jobj5 = jsonResult3.getJSONObject("img");
                        JSONArray jArray5 = jobj5.getJSONArray("alt");
                        for (int i = 0; i < jArray5.length(); i++) {

                            JSONObject jsonObject_img = jArray5.getJSONObject(i);
                            String source = jsonObject_img.getString("src");
                            Log.v("SOURCE", source);
                            HashMap<String, String> measuredResult_image_src = new HashMap<String, String>();
                            measuredResult_image_src.put(IMAGE, source);
                            myResultList_image.add(measuredResult_image_src);

                        }
                    }


                    text_to_html = jsonResult3.getString("textToHtml");
                    flash_count = jsonResult3.getString("flash_count");
                    i_frame = jsonResult3.getString("iframe_count");

                    JSONObject ext_links = jsonResult3.getJSONObject("ext");
                    if ((ext_links.optJSONArray("f") == null) && (ext_links.optJSONObject("f") == null)) {
                        ext_follow = null;

                    } else {
                        JSONArray ext_links_array = ext_links.getJSONArray("f");

                        for (int i = 0; i < ext_links_array.length(); i++) {
                            JSONObject job = ext_links_array.getJSONObject(i);

                            String img_url = job.getString("link_url");
                            ext_follow = ext_follow + img_url;
                            ext_follow = ext_follow + "!!!";

                        }

                        Log.v("LENGTH", String.valueOf(ext_links_array.length()));
                        ext = ext_links_array.length();
                    }
                    if ((ext_links.optJSONArray("nf") == null) && (ext_links.optJSONObject("nf") == null)) {
                        ext_no_follow = "no";
                        //ext_no_follow= ext_no_follow+"!!!";

                    } else {
                        JSONArray ext_links_array_no = ext_links.getJSONArray("nf");

                        for (int i = 0; i < ext_links_array_no.length(); i++) {
                            JSONObject job_no = ext_links_array_no.getJSONObject(i);

                            String img_url_no = job_no.getString("link_url");
                            ext_no_follow = ext_no_follow + img_url_no;
                            ext_no_follow = ext_no_follow + "!!!";


                        }
                        ext_n = ext_links_array_no.length();
                    }


                    JSONObject int_links = jsonResult3.getJSONObject("int");


                    if ((int_links.optJSONArray("f") == null) && (int_links.optJSONObject("f") == null)) {
                        int_follow = null;

                    } else {
                        JSONArray int_links_array = int_links.getJSONArray("f");

                        for (int i = 0; i < int_links_array.length(); i++) {
                            JSONObject int_job = int_links_array.getJSONObject(i);

                            String int_img_url = int_job.getString("link_url");
                            int_follow = int_follow + int_img_url;
                            int_follow = int_follow + "!!!";


                        }
                        in = int_links_array.length();
                    }
                    if ((int_links.optJSONArray("nf") == null) && (int_links.optJSONObject("nf") == null)) {
                        int_no_follow = "no";


                    } else {
                        JSONArray int_links_no_array = int_links.getJSONArray("nf");

                        for (int i = 0; i < int_links_no_array.length(); i++) {
                            JSONObject int_job_no = int_links_no_array.getJSONObject(i);

                            String int_img_url_no = int_job_no.getString("link_url");
                            int_no_follow = int_no_follow + int_img_url_no;
                            int_no_follow = int_no_follow + "!!!";

                        }
                        in_n = int_links_no_array.length();
                    }


                    float Total_links = ext + ext_n + in + in_n;
                    float int_fol = (ext / Total_links) * 100;
                    float int_no_fol = (ext_n / Total_links) * 100;
                    float ext_fol = (in / Total_links) * 100;
                    float ext_no_fol = (in_n / Total_links) * 100;

                    float[] links = {int_fol, int_no_fol, ext_fol, ext_no_fol};
                    yData = links;

                    Log.v("first", int_follow);
                    Log.v("sec", String.valueOf(int_no_fol));
                    Log.v("third", String.valueOf(ext_fol));
                    Log.v("fourth", String.valueOf(ext_no_fol));
                    //  yData=links;
                    page_rank = jsonResult3.getString("pagerank");

//favicon_url
                    JSONArray favi = jsonResult3.getJSONArray("favicon_url");
                    for (int i = 0; i < favi.length(); i++) {
                        String value_favi = favi.getString(i);
                        _favi = _favi + value_favi;
                        _favi = _favi + ",";
                    }

//print_css
                    printcss = jsonResult3.getString("print_css");

//open_graph
                    open_graph = jsonResult3.getString("opengraph");

//W3 validation

                    w3_validator = jsonResult3.getString("w3validity");
//Keyword_cloud-name
//one-word
                    JSONObject kw_cloud = jsonResult3.getJSONObject("kw_cloud");
                    JSONArray kw_array = kw_cloud.getJSONArray("one_word");
                    for (int i = 0; i < kw_array.length(); i++) {
                        String kwname = kw_array.getJSONObject(i).getString("kw");
                        int title = kw_array.getJSONObject(i).getInt("title");
                        int MKWS = kw_array.getJSONObject(i).getInt("m_kws");
                        int MDESC = kw_array.getJSONObject(i).getInt("m_desc");
                        int BODY = kw_array.getJSONObject(i).getInt("body");

                        tot = title + MKWS + MDESC + BODY;
                        String title2 = String.valueOf(tot);

                        HashMap<String, String> measuredResultkw_cloud_name = new HashMap<String, String>();
                        measuredResultkw_cloud_name.put(TAG_KW_NAME, kwname);
                        measuredResultkw_cloud_name.put(TAG_KW_TITLE, title2);
                        myResultList_kw_cloud_name.add(measuredResultkw_cloud_name);

                    }


                    for (int i = 0; i < kw_array.length(); i++) {
                        String kwname_tw = kw_array.getJSONObject(i).getString("kw");
                        int title = kw_array.getJSONObject(i).getInt("title");
                        int MKWS = kw_array.getJSONObject(i).getInt("m_kws");
                        int MDESC = kw_array.getJSONObject(i).getInt("m_desc");
                        int BODY = kw_array.getJSONObject(i).getInt("body");
                        freq = kw_array.getJSONObject(i).getString("density");
                        String title22 = String.valueOf(title);
                        String MKWS22 = String.valueOf(MKWS);
                        String MDES22 = String.valueOf(MDESC);
                        String BODY22 = String.valueOf(BODY);

                        HashMap<String, String> measuredResultkw_two_cloud_name = new HashMap<String, String>();
                        measuredResultkw_two_cloud_name.put(TAG_KW_TWO_NAME, kwname_tw);
                        measuredResultkw_two_cloud_name.put(TAG_KW_TWO_TITLE, title22);
                        //measuredResultkw_two_cloud_name.put(TAG_KW_TWO_MKWS, MKWS2);
                        measuredResultkw_two_cloud_name.put(TAG_KW_TWO_MDESC, MDES22);
                        measuredResultkw_two_cloud_name.put(TAG_KW_TWO_BODY, BODY22);
                        measuredResultkw_two_cloud_name.put(FREQ, freq);
                        myResultList_kw_two_cloud_name.add(measuredResultkw_two_cloud_name);
                        Log.v("FINAL", BODY22);
                        Log.v("name", kwname_tw);

                    }

                    JSONArray kw_array2 = kw_cloud.getJSONArray("two_word");
                    for (int i = 0; i < kw_array2.length(); i++) {
                        String kwname_tw2 = kw_array2.getJSONObject(i).getString("kw");
                        int title222 = kw_array2.getJSONObject(i).getInt("title");
                        int MKWS222 = kw_array2.getJSONObject(i).getInt("m_kws");
                        int MDESC222 = kw_array2.getJSONObject(i).getInt("m_desc");
                        int BODY222 = kw_array2.getJSONObject(i).getInt("body");
                        String freq2 = kw_array2.getJSONObject(i).getString("density");
                        String title2222 = String.valueOf(title222);
                        String MKWS2222 = String.valueOf(MKWS222);
                        String MDES2222 = String.valueOf(MDESC222);
                        String BODY2222 = String.valueOf(BODY222);

                        HashMap<String, String> measuredResultkw_two_two_cloud_name = new HashMap<String, String>();
                        measuredResultkw_two_two_cloud_name.put(TAG_KW_TWO_TWO_NAME, kwname_tw2);
                        measuredResultkw_two_two_cloud_name.put(TAG_KW_TWO_TWO_TITLE, title2222);
                        //measuredResultkw_two_cloud_name.put(TAG_KW_TWO_MKWS, MKWS2);
                        measuredResultkw_two_two_cloud_name.put(TAG_KW_TWO_TWO_MDESC, MDES2222);
                        measuredResultkw_two_two_cloud_name.put(TAG_KW_TWO_TWO_BODY, BODY2222);
                        measuredResultkw_two_two_cloud_name.put(FREQ_TWO, freq2);
                        myResultList_kw_two_two_cloud_name.add(measuredResultkw_two_two_cloud_name);
                        Log.v("FINAL", BODY2222);
                        Log.v("name", kwname_tw2);

                    }

                    JSONArray kw_array3 = kw_cloud.getJSONArray("three_word");
                    for (int i = 0; i < kw_array3.length(); i++) {
                        String kwname_tw3 = kw_array3.getJSONObject(i).getString("kw");
                        int title333 = kw_array3.getJSONObject(i).getInt("title");
                        int MKWS333 = kw_array3.getJSONObject(i).getInt("m_kws");
                        int MDESC333 = kw_array3.getJSONObject(i).getInt("m_desc");
                        int BODY333 = kw_array3.getJSONObject(i).getInt("body");
                        String freq3 = kw_array3.getJSONObject(i).getString("density");
                        String title3333 = String.valueOf(title333);
                        String MKWS3333 = String.valueOf(MKWS333);
                        String MDES3333 = String.valueOf(MDESC333);
                        String BODY3333 = String.valueOf(BODY333);

                        HashMap<String, String> measuredResultkw_three_cloud_name = new HashMap<String, String>();
                        measuredResultkw_three_cloud_name.put(TAG_KW_THREE_NAME, kwname_tw3);
                        measuredResultkw_three_cloud_name.put(TAG_KW_THREE_TITLE, title3333);
                        //measuredResultkw_two_cloud_name.put(TAG_KW_TWO_MKWS, MKWS2);
                        measuredResultkw_three_cloud_name.put(TAG_KW_THREE_MDESC, MDES3333);
                        measuredResultkw_three_cloud_name.put(TAG_KW_THREE_BODY, BODY3333);
                        measuredResultkw_three_cloud_name.put(FREQ_THREE, freq3);
                        myResultList_kw_three_cloud_name.add(measuredResultkw_three_cloud_name);
                        Log.v("FINAL", BODY3333);
                        Log.v("name", kwname_tw3);

                    }


                    Log.v("hitesh1", www_resolved);
                    Log.v("hitesh2", rewrite);
                    Log.v("hitesh3", ip);
                    Log.v("hitesh4", robots);
                    Log.v("hitesh5", sitemap);
                    Log.v("hitesh6", title_content);
                    Log.v("hitesh7", title_length);
                    Log.v("hitesh8", meta_desc_content);
                    Log.v("hitesh9", meta_desc_length);
                    Log.v("hitesh10", meta_kws_content);
                    Log.v("hitesh11", meta_kws_length);
                    // Log.v("hitesh12",hitesh);
                    //
                    Log.v("hitesh17", text_to_html);
                    Log.v("hitesh18", flash_count);
                    Log.v("hitesh19", i_frame);
                    Log.v("hitesh49", hitesh4);
                    //Log.v("hitesh20", arrayList.toString());
                    Log.v("hitesh21", page_rank);
                    Log.v("hitesh22", _favi);
                    Log.v("hitesh22", printcss);
                    Log.v("hitesh23", open_graph);
                    Log.v("hitesh24", w3_validator);


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
            enteringData();
            enteringData_image();
            enteringData_kw_name();
            enteringData_kw_two_cloud();
            enteringData_kw_two_two_cloud();
            enteringData_kw_three_cloud();

            TextView www_resolved = (TextView) findViewById(R.id.www_resolved);
            TextView URl_rewrite = (TextView) findViewById(R.id.url_rewrite);
            TextView IP_CANON = (TextView) findViewById(R.id.IP_CANON);
            TextView ROBO = (TextView) findViewById(R.id.ROBOTS);
            TextView Sitemap = (TextView) findViewById(R.id.textView11);
            TextView title = (TextView) findViewById(R.id.TITLE);
            TextView Meta_desc_content = (TextView) findViewById(R.id.META_DESC);

            TextView Meta_desc_length = (TextView) findViewById(R.id.META_DESC_LENGTH);

            TextView Meta_kw_content = (TextView) findViewById(R.id.META_KEYWORDS);

            TextView Meta_kw_length = (TextView) findViewById(R.id.META_KEY_LENGTH);
            TextView text_html = (TextView) findViewById(R.id.TEXT_TO_HTML);
            TextView Flash = (TextView) findViewById(R.id.FLASH);
            TextView iframe_ = (TextView) findViewById(R.id.IFRAME);
            TextView p_r = (TextView) findViewById(R.id.PAGE_RANK);
            TextView print_css = (TextView) findViewById(R.id.PRINT_CSS);
            TextView open_graph1 = (TextView) findViewById(R.id.OPEN_GRAPH);
            TextView w3c = (TextView) findViewById(R.id.W3C_VALIDATE);
            TextView favi_url = (TextView) findViewById(R.id.FAVICON);
//            TextView title =(TextView)findViewById(R.id.TITLE);
            p_r.setText(page_rank);
            print_css.setText(printcss);
            open_graph1.setText(open_graph);
            w3c.setText(w3_validator);
            favi_url.setText(_favi);
            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();

            if (www_resolve.equalsIgnoreCase("pass")) {
                www_resolved.setText("Perfect, WWW is Resolving.");
            } else {
                www_resolved.setText(" WWW is not Resolving.");
            }
            if (rewrite.equalsIgnoreCase("Yes")) {
                URl_rewrite.setText("Good, the URLs look clean.");
            } else {
                URl_rewrite.setText("No");
            }
            IP_CANON.setText(ip);
            if (robots.equalsIgnoreCase("Yes")) {
                ROBO.setText("Yes");
            } else {
                ROBO.setText("No");
            }
            Meta_desc_content.setText(meta_desc_content);
            Meta_desc_length.setText(meta_desc_length + " " + "Character(s)");
            Meta_kw_content.setText(meta_kws_content);
            Meta_kw_length.setText(meta_kws_length + " " + "Keywords(s)");
            Sitemap.setText(sitemap);
            title.setText(title_content);
            text_html.setText(text_to_html);
            if (flash_count.equalsIgnoreCase("0")) {
                Flash.setText("NO");
            } else {
                Flash.setText("Yes");
            }


            if (i_frame.equalsIgnoreCase("0")) {
                iframe_.setText("NO");
            } else {
                iframe_.setText("Yes");
            }


            mchart = new PieChart(getApplicationContext());
            linearLayout.addView(mchart);
            linearLayout.setBackgroundColor(Color.LTGRAY);
            mchart.setUsePercentValues(true);
            mchart.setDescription("PAGE LINKS");
            mchart.setDrawHoleEnabled(true);
            mchart.setHoleColorTransparent(true);
            mchart.setHoleRadius(7);
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
        }


    }
    }







/*

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            new GetMeasuredData().execute();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}*/
