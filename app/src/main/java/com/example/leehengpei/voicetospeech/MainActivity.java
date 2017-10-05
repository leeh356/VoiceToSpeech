package com.example.leehengpei.voicetospeech;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.*;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;

    //listview
    private ListView mListView;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }

    };



    //accessing listview

    //Snackbar mySnackbar = Snackbar.make(view, stringId, duration);
    //mySnackbar.show();
    //Snackbar.make(findViewById(R.id.myCoordinatorLayout), R.string.email_sent,Snackbar.LENGTH_SHORT).show();

}
