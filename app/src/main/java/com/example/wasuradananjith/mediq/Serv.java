package com.example.wasuradananjith.mediq;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by Wasura Dananjith on 26-Jan-17.
 */

public class Serv extends Service {
    Context CTX = this;
    public static int count=0;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //Toast.makeText(this,"Background Process Started", Toast.LENGTH_LONG).show();
        Log.d("myTag", "Background Process Started");

        final ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
        service.scheduleWithFixedDelay(new Runnable()
        {
            @Override
            public void run()
            {
                OnPressNotNo();
            }
        }, 0, 10, TimeUnit.SECONDS);
        //return super.onStartCommand(intent, flags, startId);
        return START_STICKY;
    }

    public void OnPressNotNo() {
        Log.d("myTag", "OnPressNotNo method is working");
        DatabaseHelper myDb = new DatabaseHelper(CTX);
        Cursor res = myDb.getAllData();
        Log.d("myTag", "Cursor res = myDb.getAllData() is working");
        while (res.moveToNext()) {
            String refNo = res.getString(1);
            String notifyNo = res.getString(2);
            Log.d("refNo", refNo);
            Log.d("notifyNo", notifyNo);
            String type = "checkCurrentNo";
            BackgroundWorker2 backgroundWorker2 = new BackgroundWorker2(this);
            backgroundWorker2.execute(type, refNo, notifyNo);
            }
    }

    @Override
    public void onDestroy(){
        //Toast.makeText(this,"Background Process Stopped", Toast.LENGTH_LONG).show();
        Log.d("myTag", "Background Process Stopped");
        super.onDestroy();
    }

    public static int showCount(){
        return count;
    }

}

