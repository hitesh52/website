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


import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.localexample.website_analyser.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity_mobile extends Activity {

    private ProgressDialog pDialog;
WebView mWebview;
    Handler handler;
    private ListView resultList_mobile;
    ArrayList<HashMap<String, String>> myResultList_mobile;

    // URL to get contacts JSON
    private static String url = "https://apiv2dev.rankwatch.com/wa/wadetails/json/";
    // JSON Node names
    String ip_tech ,city_tech,region_tech,country_tech,hosting_provider;


    String b_rate="22";
    private static String DATA_URL="cf" ;
    private static int VIEW_PORT=0;
    private static int FLASH_COUNT =0;
    private static int APPLE_ICON =0;
    private static int MOBILE_CSS =0;
    private static final String IP = "zvs";
    private static String MOBILE_RENDERING1 = "rff";


    ArrayList<String> measureList;

    // contacts JSONArray
    JSONArray result = null;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main_mobile);
        new GetMeasuredData_mobile().execute();



    }


    /**
     * Async task class to get json by making HTTP call
     * */
    private class GetMeasuredData_mobile extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(MainActivity_mobile.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            // Creating service handler class instance
            ServiceHandler_mobile sh = new ServiceHandler_mobile();
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
                    JSONObject jsonObj4= jsonObj3.getJSONObject("mobile");
                    JSONObject jsonObj5= jsonObj4.getJSONObject("mobile");
                    // looping through All result
                    DATA_URL=jsonObj5.getString("data_url");
                    VIEW_PORT=jsonObj5.getInt("viewport");
                    FLASH_COUNT=jsonObj5.getInt("flash_count");
                    APPLE_ICON=jsonObj5.getInt("apple_icon");
                    MOBILE_CSS=jsonObj5.getInt("mobile_css");
                    MOBILE_RENDERING1 = jsonObj5.getString("r");

                    Log.v("1", DATA_URL);
                    Log.v("2", String.valueOf(VIEW_PORT));
                    Log.v("3", String.valueOf(FLASH_COUNT));
                    Log.v("4", String.valueOf(APPLE_ICON));
                    Log.v("5", String.valueOf(MOBILE_CSS));
                    Log.v("7", MOBILE_RENDERING1);
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
            CheckBox view_port=(CheckBox)findViewById(R.id.VIEWPORTTAG);
            CheckBox flash_count=(CheckBox)findViewById(R.id.NOFLASHCONTENT);
            CheckBox apple_icon=(CheckBox)findViewById(R.id.APPLE_ICON);
            CheckBox mobile_render=(CheckBox)findViewById(R.id.MOBILE_RENDER);

            if(VIEW_PORT==1)
            {

                view_port.setChecked(!view_port.isChecked());
            }
            if(FLASH_COUNT==0)
            {

                flash_count.setChecked(!flash_count.isChecked());
            }
            if(APPLE_ICON==1)
            {

                apple_icon.setChecked(!apple_icon.isChecked());
            }
            if(MOBILE_CSS==1)
            {

                mobile_render.setChecked(!mobile_render.isChecked());
            }
            mWebview=(WebView)findViewById(R.id.MOBILE_VIEW);
            WebView desktopview=(WebView)findViewById(R.id.MAC_VIEW);

            mWebview.getSettings().setJavaScriptEnabled(true);
            mWebview.setWebViewClient(new WebViewClient() {
                public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                    Toast.makeText(getApplicationContext(), description, Toast.LENGTH_SHORT).show();
                }
            });

            mWebview .loadUrl(DATA_URL);

            desktopview.getSettings().setJavaScriptEnabled(true);
            desktopview.setWebViewClient(new WebViewClient() {
                public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                    Toast.makeText(getApplicationContext(), description, Toast.LENGTH_SHORT).show();
                }
            });


            desktopview.getSettings().setUserAgentString("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0");
            desktopview .loadUrl(DATA_URL);




            if (pDialog.isShowing())
                pDialog.dismiss();
        }

    }


}