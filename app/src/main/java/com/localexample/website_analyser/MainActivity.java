package com.localexample.website_analyser;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {
    EditText e1, e2;
    Button b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        e1 = (EditText) findViewById(R.id.editText);
        e2 = (EditText) findViewById(R.id.editText2);
        b1 = (Button) findViewById(R.id.button);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button) {

            String s1 = e1.getText().toString();
            String s2 = e2.getText().toString();
            if (s1.length() == 0 || s2.length() == 0) {
                Toast.makeText(this, "WRONG INPUT", 1).show();

            } else {
                DBhelper obj = new DBhelper(this);
                obj.saverecord(s1, s2);
                Toast.makeText(this, "LOGIN SUCCESSFUL", 2).show();

            }
            Intent in = new Intent(getApplicationContext(), apitask.class);

            startActivity(in);

        }

    }
}



