package com.example.myapplication1;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button start;
    private TextView textView;
    private EditText editText;
    private int min;
    private int seconds;
    private boolean isAlive = false;

    private int sec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        start = findViewById(R.id.start);
        textView = findViewById(R.id.text_view);
        editText = findViewById(R.id.edit_text);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s;
                s = editText.getText().toString();
                textView.setText(s);
                try {
                    min = Integer.parseInt(s.substring(0, s.indexOf(':')));
                    sec = Integer.parseInt(s.substring(s.indexOf(':') + 1));
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this,"Please Insert a valid time: mm/ss",Toast.LENGTH_SHORT).show();
                }
                isAlive = true;
                countdownTimer();
            }

        });
    }

    class Timer extends Thread{
        public void run(){
            seconds = 60 * min + sec;

            while (seconds != 0) {

                seconds--;
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


                try {
                    sleep(1000);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            isAlive=false;
        }
        }

    /*private void setTextColor() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                textView.setTextColor(Color.RED);
            }
        });
    }/*/

    private void setTextView(final String time){
            runOnUiThread(new Runnable() {

                @Override
                public void run() {

                    textView.setText(time);

                }
            });
        }


    private void countdownTimer(){

        Timer t=new Timer();

        t.start();
        if (!isAlive){
            textView.setTextColor(Color.RED);
        }




    }
}
