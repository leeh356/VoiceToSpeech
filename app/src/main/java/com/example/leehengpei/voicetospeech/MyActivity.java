package com.example.leehengpei.voicetospeech;

/**
 * Created by Lee Heng Pei on 3/10/2017.
 */
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;

//public class MyActivity extends Activity {

//this class is used as listeners for the buttons on the main.xaml

public class MyActivity implements View.OnClickListener {
    Button button;

    EditText e;
    private Context Activity;

    public MyActivity() {
        super();
    }

    public MyActivity(Context ctx) {
        Activity = ctx;
    }

/*
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.button1).setOnClickListener(mGlobal_OnClickListener);
        findViewById(R.id.cancel_button).setOnClickListener(mGlobal_OnClickListener);
        findViewById(R.id.share_button).setOnClickListener(mGlobal_OnClickListener);
    }*/

    /*
    public void addListenerOnButton() {

        button = (Button) findViewById(R.id.button1);
        //Inform the user the button has been clicked
        Toast.makeText(this, "Start Recording", Toast.LENGTH_SHORT).show();

        button.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Intent browserIntent =
                        new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com"));
                startActivity(browserIntent);

            }

        });

        */
    //Global On click listener for all views
    //final OnClickListener mGlobal_OnClickListener = new OnClickListener() {

        //public void onClick(final View v) {
        public void onClick(View v) {
            switch(v.getId()) {
                case R.id.button1:
                    //Inform the user the button1 has been clicked
                    //Toast.makeText(MyActivity.this, "Record button clicked.", Toast.LENGTH_SHORT).show();
                    Toast.makeText(Activity.getApplicationContext(), "Record button clicked.", Toast.LENGTH_SHORT).show();

                    break;
                case R.id.cancel_button:
                    //Inform the user the button2 has been clicked
                    Toast.makeText(Activity.getApplicationContext(), "Cancel button clicked.", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.share_button:
                    //Inform the user the button2 has been clicked
                    Toast.makeText(Activity.getApplicationContext(), "Share button clicked.", Toast.LENGTH_SHORT).show();
                    break;
            }
        };
   // };

    }


