package com.localexample.website_analyser;

/**
 * Created by Hitesh on 6/5/2015.
 */

import android.app.Activity;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class apitask1 extends Activity
{

    String URL_website = "WWW.vocabmonk.com";
    EditText etResponse;
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState)
     {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loading_website_result);
        // get reference to the views
           //etResponse = (EditText) findViewById(R.id.etResponse);
            tv = (TextView) findViewById(R.id.textView2);
            // check if you are connected or not
            if (isConnected())
            {
                tv.setBackgroundColor(0xFF00CC00);
                tv.setText("You are conncted" +
                        "LOADING RESULTS");
            } else
            {
                tv.setText("You are NOT conncted");
             }

        //call AsynTask to perform network operation on separate thread
        new HttpAsyncTask().execute("https://apiv2dev.rankwatch.com/wa/sendurl/json/");
    }
    public String GET(String url)
    {
        InputStream inputStream = null;
        String result = "";
        String result1="";
        try {

            DefaultHttpClient httpClient = new DefaultHttpClient();
            String username = "wa-v2-01-12345";
            String password = "123456789";
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
            nameValuePairs.add(new BasicNameValuePair("url", URL_website));
            nameValuePairs.add(new BasicNameValuePair("module", "seo"));
            nameValuePairs.add(new BasicNameValuePair("callback", "WWW.vocabmonk.com"));
            String paramsString = URLEncodedUtils.format(nameValuePairs, "UTF-8");
            HttpGet httpGet = new HttpGet(url + "basic-auth/user/passwd" + "?" + paramsString);
            UsernamePasswordCredentials credentials = new UsernamePasswordCredentials(username, password);
            BasicScheme scheme = new BasicScheme();
            Header authorizationHeader = scheme.authenticate(credentials, httpGet);
            httpGet.addHeader(authorizationHeader);
            HttpResponse httpResponse = httpClient.execute(httpGet);
            // receive response as inputStream
            inputStream = httpResponse.getEntity().getContent();
            // convert inputstream to string
                if (inputStream != null)
                {
                 result = convertInputStreamToString(inputStream);
                 JSONObject json = new JSONObject(result);
                 JSONObject json_LL = json.getJSONObject("data");
                 String str_value=json_LL.getString("id");
                 result1 = str_value;
                }
               else
                {
                  result = "Did not work!";

                }
              } catch (Exception e) {
                 Log.d("InputStream", e.getLocalizedMessage());
                 }

        return result1;
     }

    private static String convertInputStreamToString(InputStream inputStream) throws IOException
    {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String line = "";
        String result = "";
        while ((line = bufferedReader.readLine()) != null)
            result += line;

        inputStream.close();
        return result;

    }

    public boolean isConnected()
    {
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Activity.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected())
            return true;
        else
            return false;
    }

    public class HttpAsyncTask extends AsyncTask<String, Void, String>
    {
        @Override
        protected String doInBackground(String... urls)
        {
            return GET(urls[0]);
        }

        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result)
        {
            Toast.makeText(getBaseContext(), "Received!", Toast.LENGTH_LONG).show();
           // etResponse.setText(result);
            Intent i =new Intent(apitask1.this ,apitask2.class);
            i.putExtra("api_id",result);
            startActivity(i);
        }
    }

}