package com.example.myapplication1.main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication1.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button start;
    private TextView textView;
    private TextView nameText;
    private EditText editText;
    private int seconds;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        start = findViewById(R.id.start);
        textView = findViewById(R.id.text_view);
        editText = findViewById(R.id.edit_text);
        nameText=findViewById(R.id.name_surname);

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


}
