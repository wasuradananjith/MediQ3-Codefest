package com.example.wasuradananjith.mediq;

import android.app.Application;
import android.content.Intent;

/**
 * Created by Wasura Dananjith on 28-Jan-17.
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        startService(new Intent(this, Serv.class));
    }
}
