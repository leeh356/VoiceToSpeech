package com.example.leehengpei.voicetospeech;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
//import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

/**
 * Created by Lee Heng Pei on 18/10/2017.
 */

public class Alert extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        String msg = getString(R.string.alarmtext) + getIntent().getExtras().getString(getString(R.string.title_msg));
        builder.setMessage(msg).setCancelable(
                false).setPositiveButton(getString(R.string.ok),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        Alert.this.finish();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }

}
