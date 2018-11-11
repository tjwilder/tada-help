package com.khalory.tadahelp;

import android.content.Intent;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Locale;

public class WorkingActivity extends AppCompatActivity {

    TextView timer;
    long millisecondTime, startTime, timeBuff, updateTime = 0L;
    Handler handler;
    int seconds, minutes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_working);

        handler = new Handler();

        startTime = SystemClock.uptimeMillis();
        handler.postDelayed(timeUpdater, 0);
    }

    public void handleNext(View view) {
        Intent intent = new Intent(this, HelpActivity.class);
        EditText nameText = findViewById(R.id.nameText);

        startActivity(intent);
    }

    public Runnable timeUpdater = new Runnable() {
        public void run() {
            millisecondTime = SystemClock.uptimeMillis() - startTime;
            updateTime = timeBuff + millisecondTime;

            seconds = (int) (updateTime / 1000);
            minutes = seconds / 60;
            seconds = seconds % 60;

            timer.setText(String.format(Locale.getDefault(), getString(R.string.timerFormat), minutes, seconds));

            handler.postDelayed(this, 0);
        }
    };
}
