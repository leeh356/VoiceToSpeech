package com.example.leehengpei.voicetospeech;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.*;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import static android.Manifest.permission.RECORD_AUDIO;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import java.util.Random;
import android.media.MediaPlayer;
import android.media.MediaRecorder;


public class MainActivity extends AppCompatActivity {

    //private TextView mTextMessage;
    TextView mTextMessage;

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

    SQLiteDatabase db;
    DbHelper mDbHelper;
    ListView list;

    String AudioSavePathInDevice = null;
    MediaRecorder mediaRecorder ;
    Random random ;
    String RandomAudioFileName = "ABCDEFGHIJKLMNOP";
    public static final int RequestPermissionCode = 1;
    MediaPlayer mediaPlayer ;
    View   btn_record , btn_cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list = (ListView)findViewById(R.id.commentslist);
        mDbHelper = new DbHelper(this);
        db= mDbHelper.getWritableDatabase();
        final ImageView alarmImage = (ImageView) findViewById(R.id.alarmImage);

        String[] from = {mDbHelper.TITLE, mDbHelper.DETAIL, mDbHelper.TYPE, mDbHelper.TIME, mDbHelper.DATE};
        final String[] column = {mDbHelper.C_ID, mDbHelper.TITLE, mDbHelper.DETAIL, mDbHelper.TYPE, mDbHelper.TIME, mDbHelper.DATE};
        int[] to = {R.id.title, R.id.Detail, R.id.type, R.id.time, R.id.date};

        final Cursor cursor = db.query(mDbHelper.TABLE_NAME, column, null, null ,null, null, null);
        final SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.list_entry, cursor, from, to, 0);

        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView<?> listView, View view, int position,
                                    long id){
                Intent intent = new Intent(MainActivity.this, View_Note.class);
                intent.putExtra(getString(R.string.rodId), id);
                startActivity(intent);

               // mTextMessage = (TextView) findViewById(R.id.message);
                BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
                navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

                btn_record = findViewById(R.id.button1);
                //btn_record.setOnClickListener(new MyActivity(getApplicationContext()));
                //btn_record.setOnClickListener(mGlobal_OnClickListener);

                btn_record.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if(checkPermission()) {
                            AudioSavePathInDevice = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + CreateRandomAudioFileName(5) + "AudioRecording.3gp";
                            MediaRecorderReady();

                            System.out.printf("RECORDING");

                            try {
                                mediaRecorder.prepare();
                                mediaRecorder.start();
                            } catch (IllegalStateException e) {
                                // TODO Auto-generated catch block
                                System.out.printf("ILLEGAL STATE EXCEPTION");
                                e.printStackTrace();
                            } catch (IOException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                                System.out.printf("IO EXCEPTION");
                            }

                            btn_record.setEnabled(false);
                            btn_cancel.setEnabled(true);

                            Toast.makeText(MainActivity.this, "Recording started", Toast.LENGTH_LONG).show();
                        }
                        else {

                            requestPermission();

                        }

                    }
                });


                //Button btn_cancel= findViewById(R.id.cancel_button);
               // btn_cancel.setOnClickListener(new MyActivity(getApplicationContext()));
                //btn_cancel.setOnClickListener(mGlobal_OnClickListener);

                //Button btn_share = findViewById(R.id.share_button);
                //btn_share.setOnClickListener(new MyActivity(getApplicationContext()));
               // btn_share.setOnClickListener(mGlobal_OnClickListener);


            }

        });

    }

    public void MediaRecorderReady(){
        mediaRecorder=new MediaRecorder();
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mediaRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
        mediaRecorder.setOutputFile(AudioSavePathInDevice);
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(MainActivity.this, new String[]{WRITE_EXTERNAL_STORAGE, RECORD_AUDIO}, RequestPermissionCode);
    }

    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case RequestPermissionCode:
                if (grantResults.length > 0) {

                    boolean StoragePermission = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    boolean RecordPermission = grantResults[1] == PackageManager.PERMISSION_GRANTED;

                    if (StoragePermission && RecordPermission) {

                        Toast.makeText(MainActivity.this, "Permission Granted", Toast.LENGTH_LONG).show();
                    }
                    else {
                        Toast.makeText(MainActivity.this,"Permission Denied",Toast.LENGTH_LONG).show();

                    }
                }

                break;
        }
    }

    public boolean checkPermission() {

        int result = ContextCompat.checkSelfPermission(getApplicationContext(), WRITE_EXTERNAL_STORAGE);
        int result1 = ContextCompat.checkSelfPermission(getApplicationContext(), RECORD_AUDIO);

        return result == PackageManager.PERMISSION_GRANTED && result1 == PackageManager.PERMISSION_GRANTED;
    }

    public String CreateRandomAudioFileName(int string){

        StringBuilder stringBuilder = new StringBuilder( string );

        int i = 0 ;
        while(i < string ) {

            stringBuilder.append(RandomAudioFileName.charAt(random.nextInt(RandomAudioFileName.length())));

            i++ ;
        }
        return stringBuilder.toString();

    }
    //end of recording audio file

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()) {
            case R.id.action_new:
                Intent openCreateNote = new Intent(MainActivity.this, CreateNote.class);
                startActivity(openCreateNote);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    final OnClickListener mGlobal_OnClickListener = new OnClickListener() {

        public void onClick(final View v) {
        //public void onClick(View v) {
            switch(v.getId()) {
                case R.id.button1:
                    //Inform the user the button1 has been clicked
                    //Toast.makeText(MyActivity.this, "Record button clicked.", Toast.LENGTH_SHORT).show();
                    Toast.makeText(MainActivity.this, "Record button clicked.", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.cancel_button:
                    //Inform the user the button2 has been clicked
                    //Toast.makeText(Activity.getApplicationContext(), "Cancel button clicked.", Toast.LENGTH_SHORT).show();
                    Toast.makeText(MainActivity.this, "Cancel button clicked.", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.share_button:
                    //Inform the user the button2 has been clicked
                    //Toast.makeText(Activity.getApplicationContext(), "Share button clicked.", Toast.LENGTH_SHORT).show();
                    Toast.makeText(MainActivity.this, "Share button clicked.", Toast.LENGTH_SHORT).show();
                     break;

            }
        };
    };



}
