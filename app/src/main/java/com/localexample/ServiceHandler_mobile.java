package com.localexample;

import android.util.Log;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.auth.AuthenticationException;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class ServiceHandler_mobile {

    static String response = null;

    public final static int GET = 1;
    public final static int POST = 2;
    InputStream is = null;
    String result = "";

    public ServiceHandler_mobile() {

    }

    /*
     * Making service call
     * @url - url to make request
     * @method - http request method
     * */
    public String makeServiceCall(String url, int method) {
        return this.makeServiceCall(url, method, null);
    }

    /*
     * Making service call
     * @url - url to make request
     * @method - http request method
     * @params - http request params
     * */
    public String makeServiceCall(String url, int method,
                                  List<NameValuePair> params) {
        try {
            // http client
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpEntity httpEntity = null;
            HttpResponse httpResponse = null;

            // Checking http request method type
            if (method == POST) {
                HttpPost httpPost = new HttpPost(url);
                // adding post params
                if (params != null) {
                    httpPost.setEntity(new UrlEncodedFormEntity(params));
                }

                httpResponse = httpClient.execute(httpPost);

            } else if (method == GET) {
                // appending params to url
                String username = "wa-v2-01-12345";
                String password = "123456789";
                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
                nameValuePairs.add(new BasicNameValuePair("id", "e89bb4bdb971083322c3e663929facb6"));
                nameValuePairs.add(new BasicNameValuePair("callback", "WWW.vocabmonk.com"));
                String paramsString = URLEncodedUtils.format(nameValuePairs, "UTF-8");
                HttpGet httpGet = new HttpGet(url + "basic-auth/user/passwd" + "?" + paramsString);
                UsernamePasswordCredentials credentials = new UsernamePasswordCredentials(username, password);
                BasicScheme scheme = new BasicScheme();
                Header authorizationHeader = scheme.authenticate(credentials, httpGet);
                httpGet.addHeader(authorizationHeader);
                httpResponse = httpClient.execute(httpGet);
                try {

                    // Parse the data into jsonobject to get original data in form of
                    // json.
                    JSONObject jObject = new JSONObject(response);
                    JSONObject jObj = jObject;

                    Log.v("JsonParser", "JsonByteArry data: " + jObj.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
            httpEntity = httpResponse.getEntity();
            is = httpEntity.getContent();

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (AuthenticationException e) {
            e.printStackTrace();
        }

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(is, "utf-8"), 8);
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
                result=result+line;
            }
            result = sb.toString();
        } catch (Exception e) {
            return null;
        }

        // Convert string to object
        try {
            JSONObject jsonObject = new JSONObject(result);
        } catch (JSONException e) {
            return null;
        }
        return result;

    }
}

