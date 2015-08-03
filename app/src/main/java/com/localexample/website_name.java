package com.localexample;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.localexample.website_analyser.R;

import java.util.HashMap;

//ACTIVITY FOR ASKING THE USER TO ENTER WEBSITE WHICH HAS TO BE ANALYSED
//LAYOUT USED HERE IS WEBSITE
public class website_name extends Activity {
    EditText et;
    SessionManager session;

    @Override

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.website);
        et = (EditText) findViewById(R.id.editText1);
        Button bt = (Button) findViewById(R.id.button1);
        //LISTENING TO BUTTON EVENT
        session = new SessionManager(getApplicationContext());
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                //DEFINING NEW INTENT TO TRIGGER NEW ACTIVITY - APITASK1
                //ALSO SENDING THE WEBSITE NAME TO APITASK1 TO MAKE A HTTP GET REQUEST TO IT
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                i.putExtra("website",et.getText());
                   Log.v("hitesh3",et.getText().toString());
                startActivity(i);

            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater Inflater = getMenuInflater();
        Inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.logout) {
            Toast.makeText(getApplicationContext(), "User Login Status: " + session.isLoggedIn(), Toast.LENGTH_LONG).show();
            session.checkLogin();
            HashMap<String, String> user = session.getUserDetails();
            String username = user.get(SessionManager.KEY_USERNAME);
            String pass = user.get(SessionManager.KEY_PASSWORD);
            Log.v("USERNMA",username);
            Log.v("PASSWORD",pass);
            session.logoutUser();
        }
            return super.onOptionsItemSelected(item);
        }

    }

