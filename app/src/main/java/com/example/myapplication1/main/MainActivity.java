package com.example.myapplication1.main;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication1.R;
import com.example.myapplication1.databinding.MyCardViewBinding;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private final int CHANNEL_ID = 001;
    private final String NOTIFICATION_ID ="my channel";

    private Button start;
    private TextView textView;
    private TextView nameText;
    private EditText editText;
    private int seconds;
    MyCardViewBinding myCardViewBinding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        start = findViewById(R.id.start);
        textView = findViewById(R.id.text_view);
        editText = findViewById(R.id.edit_text);
        nameText=findViewById(R.id.name_surname);

        Toast.makeText(this,"Please Enter Time",Toast.LENGTH_LONG).show();

        setData();
        start.setOnClickListener(this);
        
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.start:{
                start();
            }
        }
        
    }

    private void start() {
        String s=editText.getText().toString();

        try {
            int min = Integer.parseInt(s.substring(0, s.indexOf(':')));
            int sec = Integer.parseInt(s.substring(s.indexOf(':') + 1));
            seconds = min*60+sec;

        } catch (Exception e) {
            Toast.makeText(MainActivity.this,"Please Insert a valid time: mm/ss",Toast.LENGTH_SHORT).show();
        }
        updateTextView(seconds);
        countdownTimer();
    }
    private void updateTextView(int seconds){

        if (seconds/60>=10&&seconds%60>=10) {
            setTextView(seconds / 60 +":"+ seconds % 60);
        }else
        if (seconds/60<10&&seconds%60<10){
            setTextView("0"+seconds / 60 +":"+ "0"+seconds % 60);
        }else if (seconds/60<10&&seconds%60>=10) {
            setTextView("0" + seconds / 60 + ":" + seconds % 60);
        }else if (seconds/60>=10&&seconds%60<10){
            setTextView(seconds / 60 +":"+ "0"+seconds % 60);
        }

    }

    private void setTextView(final String time){
        runOnUiThread(new Runnable() {

            @Override
            public void run() {
                textView.setText(time);
                if(seconds==0){
                    textView.setTextColor(Color.RED);
                    nameText.setTextColor(Color.RED);
                    sendNotification();
                }

            }
        });
    }



    private void countdownTimer(){

        Thread t=new Thread(){
            public void run(){
                while (seconds != 0) {
                    seconds--;
                    updateTextView(seconds);
                    try {
                        sleep(1000);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        t.start();
    }

    private void setData() {

        Intent intent= getIntent();
        String text = intent.getStringExtra("name");
        nameText.setText(text);

    }

    private void sendNotification() {

        createNotifChannel();


        //creating notification builder with properties
        NotificationCompat.Builder builder= new NotificationCompat.Builder(this, NOTIFICATION_ID)
                .setSmallIcon(R.drawable.alert)
                .setContentTitle("Timer")
                .setContentText("Time is out");

       //Attaching Action
        //opens new activity
        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(contentIntent);


        //issuing the notification
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(CHANNEL_ID,builder.build());


    }


        //creating t he channel for high API's
        //For Oreo and up we have to create channels
    private void createNotifChannel() {
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.O) {

            CharSequence name = "Timer Notification Channel";
            String description = "Out of time notification channel";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;

            NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_ID, name, importance);
            notificationChannel.setDescription(description);

            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(notificationChannel);

        }



    }


}
