package com.example.wasuradananjith.mediq;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class LoadingHome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {

            public void run() {

                startActivity(new Intent(LoadingHome.this,Welcome.class));
                finish();
            }

        }, 3000);
    }
}
