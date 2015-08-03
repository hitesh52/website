package com.localexample;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.ListView;
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

public class MainActivity_spell extends Activity {
    InputStream is = null;
    String result = null;
    private ProgressDialog pDialog;
    List<ListItem_spell> dataList_spell;
    Handler handler;
    private ListView resultList_spell;

    ArrayList<HashMap<String, String>> myResultList_spell;
    CustomListAdapter_spell adapter_spell;
    // URL to get contacts JSON
    private static String url = "https://apiv2dev.rankwatch.com/wa/wadetails/json/";
    // JSON Node names
    private static final String WRONG_WORD = "wrong_word";
    private static final String SUBSTITUTIONS = "substitutions";
    private static final String INPUT_TEXT = "inputText";
    ArrayList<String> measureList;
    SMSBlockerDataBaseAdapter spell;
    // contacts JSONArray



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_spell);
        myResultList_spell = new ArrayList<HashMap<String, String>>();
        spell=new SMSBlockerDataBaseAdapter(this);
        try {
            spell=spell.open();
            spell.insertEntry("www.vocabmonk.com","spell","5041f147fb3a13b51861ccd611ca7214",898);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String module=spell.getSinlgeEntry("www.vocabmonk.com","spell");
        Log.v("MODULE",module);

        if("spell".equals(module))
        {
            Toast.makeText(MainActivity_spell.this, "Congrats: ID AVAILABLE", Toast.LENGTH_LONG).show();

        }
        else
        {
            Toast.makeText(MainActivity_spell.this, "ID NOT VAILABLE", Toast.LENGTH_LONG).show();
            // Creating service handler class instance
          //  Intent i =new Intent(getApplicationContext(),apitask1.class);
            //startActivity(i);


        }
        new GetMeasuredData_spell().execute();
        dataList_spell = new ArrayList<>();
        resultList_spell= (ListView) findViewById(R.id.listView_spell);

    }

    public void enteringData_spell(){
        for(int i=0; i < myResultList_spell.size();i++){
            HashMap<String, String> hashMap_spell = new HashMap<String, String>();
            hashMap_spell = myResultList_spell.get(i);
            dataList_spell.add(new ListItem_spell(hashMap_spell.get(WRONG_WORD).toString(), hashMap_spell.get(SUBSTITUTIONS).toString(), hashMap_spell.get(INPUT_TEXT)));
            adapter_spell = new CustomListAdapter_spell(this, R.layout.list_item_spell, dataList_spell);
            adapter_spell.notifyDataSetChanged();
            resultList_spell.setAdapter(adapter_spell);
        }
    }
    /**
     * Async task class to get json by making HTTP call
     * */
    private class GetMeasuredData_spell extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(MainActivity_spell.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            // Creating service handler class instance

            // Making a request to url and getting response
            try {

                String result = "";
                HttpEntity httpEntity = null;
                // HttpResponse httpResponse = null;
                DefaultHttpClient httpClient = new DefaultHttpClient();
                String username= "wa-v2-01-12345";
                String password ="123456789";

                 String passwd = spell.getSinlgeEntry2("www.vocabmonk.com","spell");
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
                spell.close();
            } catch (Exception e) {
                Log.d("InputStream", e.getLocalizedMessage());
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

            Log.d("Response: ", "> " + result);
            if (result != null) {
                try {
                    JSONObject jsonObj = new JSONObject(result);
                    // Getting JSON Array node
                    JSONObject jsonObj1= jsonObj.getJSONObject("data");
                    JSONObject jsonObj2= jsonObj1.getJSONObject("response");
                    JSONObject jsonObj3= jsonObj2.getJSONObject("data");
                    JSONObject jsonObj4= jsonObj3.getJSONObject("spell");
                    JSONObject jsonObj5= jsonObj4.getJSONObject("spell");
                    JSONArray jsonObj6= jsonObj5.getJSONArray("spell_data");
                    // looping through All result

                    for (int i = 0; i < jsonObj6.length(); i++) {
                        JSONObject c = jsonObj6.getJSONObject(i);
                        Object intervention = c.get("data");
                           if (intervention instanceof JSONArray) {
                              // It's an array
                               JSONObject jii =jsonObj6.getJSONObject(i);
                               String input_text= jii.getString("inputText");
                               JSONArray jArray1 = c.getJSONArray("data");
                               for (int j = 0; j <  jArray1.length(); j++) {
                                   JSONObject value = jArray1.getJSONObject(j);
                                   String wrong_word= value.getString("wrong_word");
                                   String substitutions= value.getString("substitutions");
                                   HashMap<String, String> measuredResult_spell = new HashMap<String, String>();
                                   measuredResult_spell.put(WRONG_WORD,wrong_word);
                                   measuredResult_spell.put(SUBSTITUTIONS,substitutions);
                                   Log.v("OBBBB", wrong_word);
                                   Log.v("OBBB", substitutions);
                                   measuredResult_spell.put(INPUT_TEXT, input_text);
                                   myResultList_spell.add(measuredResult_spell);

                               }
                             } else {






                               JSONObject jobj= c.getJSONObject("data");
                               String input =c.getString("inputText");
                               if (!(jobj.optJSONObject("1") == null))  {
                                   JSONObject jobjSS= jobj.getJSONObject("1");
                                   HashMap<String, String> measuredResult1 = new HashMap<String, String>();
                                   String wrong_word1= jobjSS.getString("wrong_word");
                                   String substitutions1= jobjSS.getString("substitutions");
                                   //String input_text =jobjSS.getString("inputText");
                                   measuredResult1.put(WRONG_WORD,wrong_word1);
                                   measuredResult1.put(SUBSTITUTIONS,substitutions1);
                                   measuredResult1.put(INPUT_TEXT,input);
                                   myResultList_spell.add(measuredResult1);

                               }
                               if (!(jobj.optJSONObject("2") == null))  {
                                   JSONObject jobjSS2= jobj.getJSONObject("2");
                                   HashMap<String, String> measuredResult2 = new HashMap<String, String>();
                                   String wrong_word2= jobjSS2.getString("wrong_word");
                                   String substitutions2= jobjSS2.getString("substitutions");
                                   //String input_text =jobjSS.getString("inputText");
                                   measuredResult2.put(WRONG_WORD,wrong_word2);
                                   measuredResult2.put(SUBSTITUTIONS,substitutions2);
                                   measuredResult2.put(INPUT_TEXT,input);
                                   myResultList_spell.add(measuredResult2);

                               }
                               if (!(jobj.optJSONObject("3") == null))  {
                                   JSONObject jobjSS3= jobj.getJSONObject("3");
                                   HashMap<String, String> measuredResult3 = new HashMap<String, String>();
                                   String wrong_word3= jobjSS3.getString("wrong_word");
                                   String substitutions3= jobjSS3.getString("substitutions");
                                   //String input_text =jobjSS.getString("inputText");
                                   measuredResult3.put(WRONG_WORD,wrong_word3);
                                   measuredResult3.put(SUBSTITUTIONS,substitutions3);
                                   measuredResult3.put(INPUT_TEXT,input);
                                   myResultList_spell.add(measuredResult3);

                               }
                               if (!(jobj.optJSONObject("4") == null))  {
                                   JSONObject jobjSS4= jobj.getJSONObject("4");
                                   HashMap<String, String> measuredResult4 = new HashMap<String, String>();
                                   String wrong_word4= jobjSS4.getString("wrong_word");
                                   String substitutions4= jobjSS4.getString("substitutions");
                                   //String input_text =jobjSS.getString("inputText");
                                   measuredResult4.put(WRONG_WORD,wrong_word4);
                                   measuredResult4.put(SUBSTITUTIONS,substitutions4);
                                   measuredResult4.put(INPUT_TEXT,input);
                                   myResultList_spell.add(measuredResult4);

                               }
                               if (!(jobj.optJSONObject("5") == null))  {
                                   JSONObject jobjSS5= jobj.getJSONObject("5");
                                   HashMap<String, String> measuredResult5 = new HashMap<String, String>();
                                   String wrong_word5= jobjSS5.getString("wrong_word");
                                   String substitutions5= jobjSS5.getString("substitutions");
                                   //String input_text =jobjSS.getString("inputText");
                                   measuredResult5.put(WRONG_WORD,wrong_word5);
                                   measuredResult5.put(SUBSTITUTIONS,substitutions5);
                                   measuredResult5.put(INPUT_TEXT,input);
                                   myResultList_spell.add(measuredResult5);

                               }

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
            enteringData_spell();
            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();
        }

    }


}