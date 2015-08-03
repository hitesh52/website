package com.localexample;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.ProgressBar;
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

public class MainActivity_mobile extends Activity {

    private ProgressDialog pDialog;
     WebView web;
    Handler handler;
    ProgressBar progressBar;
    private ListView resultList_mobile;
    ArrayList<HashMap<String, String>> myResultList_mobile;
    private ProgressDialog progressDialog;
    // URL to get contacts JSON
    private static String url = "https://apiv2dev.rankwatch.com/wa/wadetails/json/";
    // JSON Node names
    String ip_tech ,city_tech,region_tech,country_tech,hosting_provider;

    InputStream is = null;
    String result1 = null;
    String b_rate="22";
    private static String DATA_URL="cf" ;
    private static int VIEW_PORT=0;
    private static int FLASH_COUNT =0;
    private static int APPLE_ICON =0;
    private static int MOBILE_CSS =0;
    private static final String IP = "zvs";
    private static String MOBILE_RENDERING1 = "rff";
    SMSBlockerDataBaseAdapter mobile;

    ArrayList<String> measureList;

    // contacts JSONArray
    JSONArray result = null;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main_mobile);




        mobile=new SMSBlockerDataBaseAdapter(this);
        try {
            mobile=mobile.open();
            mobile.insertEntry("www.vocabmonk.com", "mobile", "e89bb4bdb971083322c3e663929facb6", 898);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String module=mobile.getSinlgeEntry("www.vocabmonk.com","mobile");
        Log.v("MODULE", module);

        if("mobile".equals(module))
        {
            Toast.makeText(MainActivity_mobile.this, "Congrats: ID AVAILABLE", Toast.LENGTH_LONG).show();

        }
        else
        {
            Toast.makeText(MainActivity_mobile.this, "ID NOT VAILABLE", Toast.LENGTH_LONG).show();
            //Creating service handler class instance
              Intent i =new Intent(getApplicationContext(),apitask_mobile.class);
             startActivity(i);


        }
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
            try {


                HttpEntity httpEntity = null;
                // HttpResponse httpResponse = null;
                DefaultHttpClient httpClient = new DefaultHttpClient();
                String username= "wa-v2-01-12345";
                String password ="123456789";

                String passwd =mobile.getSinlgeEntry2("www.vocabmonk.com","mobile");
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
                mobile.close();
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

            web = (WebView) findViewById(R.id.MOBILE);
            progressBar = (ProgressBar) findViewById(R.id.progressBar1);

            web.setWebViewClient(new myWebClient());
            web.getSettings().setJavaScriptEnabled(true);
            web.loadUrl(DATA_URL);






























         /*   mWebview= (WebView) findViewById(R.id.MOBILE);

            //final Activity activity = this;

            mWebview.getSettings().setJavaScriptEnabled(true);
            pbr = (ProgressBar)findViewById(R.id.progressBar1);


            mWebview.loadUrl(DATA_URL);

           mWebview.setWebViewClient(new WebViewClient() {
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    view.loadUrl(url);
                    return true;
                }

                @Override
                public void onPageStarted(WebView view, String url, Bitmap favicon) {
                    super.onPageStarted(view, url, favicon);
                    // Show progressbar
                   pbr.setVisibility(View.VISIBLE);
                }

                @Override
                public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                    // Show error
                    // Stop spinner or progressbar
                    pbr.setVisibility(View.GONE);

                }


                @Override
                public void onPageFinished(WebView view, String url) {
                    super.onPageFinished(view, url);
                    // Stop spinner or progressBar
                    pbr.setVisibility(View.GONE);

                }


            });*/


/*
            mWebview=(WebView)findViewById(R.id.MOBILE_VIEW);
            WebView desktopview=(WebView)findViewById(R.id.MAC_VIEW);

            mWebview.getSettings().setJavaScriptEnabled(true);
            mWebview.setWebViewClient(new WebViewClient() {
                public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                    Toast.makeText(getApplicationContext(), description, Toast.LENGTH_SHORT).show();
                }
            });

            mWebview .loadUrl(DATA_URL);*/
            WebView desktopview=(WebView)findViewById(R.id.MAC_VIEW);
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
    private class myWebClient extends WebViewClient {
        @Override
        public void onPageStarted(WebView view, String url1, Bitmap favicon) {
            // TODO Auto-generated method stub
            super.onPageStarted(view, url1, favicon);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url1) {
            // TODO Auto-generated method stub
            progressBar.setVisibility(View.VISIBLE);
            view.loadUrl(url1);
            return true;

        }

        @Override
        public void onPageFinished(WebView view, String url1) {
            // TODO Auto-generated method stub
            super.onPageFinished(view, url1);

            progressBar.setVisibility(View.GONE);
        }
    }

}
