package com.localexample.website_analyser;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class apitask extends Activity {
    EditText et;
    @Override

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.website);
         et = (EditText) findViewById(R.id.editText);
        Button bt = (Button) findViewById(R.id.button1);
        //Listening to button event
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent i = new Intent(getApplicationContext(),apitask1.class);
                startActivity(i);
            }
        });
    }
}

