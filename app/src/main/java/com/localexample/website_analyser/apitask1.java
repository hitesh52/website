package com.localexample.website_analyser;

/**
 * Created by Hitesh on 6/5/2015.
 */

import android.app.Activity;
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
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;

public class apitask1 extends Activity {
    EditText etResponse;
        TextView tvIsConnected;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.website_result);

            // get reference to the views
            etResponse = (EditText) findViewById(R.id.etResponse);
            tvIsConnected = (TextView) findViewById(R.id.tvIsConnected);

            // check if you are connected or not
           /* if(isConnected()){
                tvIsConnected.setBackgroundColor(0xFF00CC00);
                tvIsConnected.setText("You are conncted");
            }
            else{
                tvIsConnected.setText("You are NOT conncted");
            }

            */// call AsynTask to perform network operation on separate thread
            new HttpAsyncTask().execute("https://apiv2dev.rankwatch.com/wa/sendurl/json/");
        }

        public String GET(String url){
            InputStream inputStream = null;
            String result = "";
            try {

                DefaultHttpClient httpClient = new DefaultHttpClient();
                String user= "wa-v2-01-12345";
                String passwd ="123456789";
                //HttpGet httpGet = new HttpGet(url+"basic-auth/user/passwd"+"?"+"callback=www.vocabmonk.com&module=seo&url=www.vocabmonk.com");
                HttpGet request = new HttpGet();
                request.setURI(new URI(url));
                UsernamePasswordCredentials credentials = new UsernamePasswordCredentials(user,passwd);
                BasicScheme scheme = new BasicScheme();
                Header authorizationHeader = scheme.authenticate(credentials, request);
                request.addHeader(authorizationHeader);
                HttpResponse httpResponse = httpClient.execute(new HttpGet(url));
                // receive response as inputStream
                inputStream = httpResponse.getEntity().getContent();

                // convert inputstream to string
                if(inputStream != null)
                    result = convertInputStreamToString(inputStream);
                else
                    result = "Did not work!";

            } catch (Exception e) {
                Log.d("InputStream", e.getLocalizedMessage());
            }

            return result;
        }

        private static String convertInputStreamToString(InputStream inputStream) throws IOException{
            BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
            String line = "";
            String result = "";
            while((line = bufferedReader.readLine()) != null)
                result += line;

            inputStream.close();
            return result;

        }

        public boolean isConnected(){
            ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Activity.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.isConnected())
                return true;
            else
                return false;
        }
        private class HttpAsyncTask extends AsyncTask<String, Void, String> {
            @Override
            protected String doInBackground(String... urls) {

                return GET(urls[0]);
            }
            // onPostExecute displays the results of the AsyncTask.
            @Override
            protected void onPostExecute(String result) {
                Toast.makeText(getBaseContext(), "Received!", Toast.LENGTH_LONG).show();
                etResponse.setText(result);
            }
        }
    }