package com.localexample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.localexample.website_analyser.R;

//ACTIVITY FOR ASKING THE USER TO ENTER WEBSITE WHICH HAS TO BE ANALYSED
//LAYOUT USED HERE IS WEBSITE
public class website_name extends Activity {
    EditText et;
    @Override

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.website);
         et = (EditText) findViewById(R.id.editText1);
        Button bt = (Button) findViewById(R.id.button1);
        //LISTENING TO BUTTON EVENT
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                //DEFINING NEW INTENT TO TRIGGER NEW ACTIVITY - APITASK1
                //ALSO SENDING THE WEBSITE NAME TO APITASK1 TO MAKE A HTTP GET REQUEST TO IT
                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                i.putExtra("website",et.getText().toString());
                //Log.v("hitesh3",et.getText().toString());
                startActivity(i);

            }
        });
    }
}

