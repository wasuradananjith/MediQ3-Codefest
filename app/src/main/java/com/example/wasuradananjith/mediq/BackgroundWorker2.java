package com.example.wasuradananjith.mediq;

import android.app.AlertDialog;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by Wasura Dananjith on 26-Jan-17.
 */

public class BackgroundWorker2 extends AsyncTask<String,Void,String> {
    String ref_Number;
    String notify_Number;
    Context context;
    BackgroundWorker2 (Context ctx) {
        context = ctx;
    }
    @Override
    protected String doInBackground(String... params) {
        String type = params[0];
        ref_Number=params[1];
        notify_Number=params[2];
        //String current_No_url = "http://10.0.2.2/notifyNo.php";
        String current_No_url= "http://mediq.000webhostapp.com/notifyNo.php";
        if(type.equals("checkCurrentNo")){
            try {
                URL url = new URL(current_No_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("ref_Number", "UTF-8") + "=" + URLEncoder.encode(ref_Number, "UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String result = "";
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                if (result.equals(notify_Number)){
                    result = notify_Number;
                }
                else if(result.equals("-1"))
                    result="-1";
                else if (result.equals("-2"))
                    result="-2";
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    protected void onPostExecute(String result ) {
        /*NotificationCompat.Builder mBuilder =
                        new NotificationCompat.Builder(Serv.this)
                                .setSmallIcon(R.drawable.notificationicon)
                                .setContentTitle("My notification")
                                .setContentText("Hello World!");
                NotificationManager mNotifyMgr = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                mNotifyMgr.notify(0, mBuilder.build());*/
        if (result.equals("-1")){
            if (PhoneNumber.cameCount==0){
                Log.d("Alert : "+ref_Number, "Doctor has COME.");
                NotificationCompat.Builder mBuilder =
                        new NotificationCompat.Builder(context)
                                .setSmallIcon(R.drawable.notificationicon)
                                .setContentTitle("Alert : "+ref_Number)
                                .setContentText("Doctor has COME.");
                NotificationManager mNotifyMgr =
                        (NotificationManager) context.getSystemService(android.content.Context.NOTIFICATION_SERVICE);
                Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                mBuilder.setSound(alarmSound);
                mBuilder.setVibrate(new long[] { 1000, 1000, 1000, 1000, 1000 });
                mBuilder.setLights(Color.RED, 3000, 3000);
                mNotifyMgr.notify(0, mBuilder.build());
                PhoneNumber.cameCount++;
                PhoneNumber.outCount=0;
            }
            else{
                Log.d("Alert : "+ref_Number, "Doctor has COME. But NO NOTIFICATION.");
            }

        }
        else if (result.equals("-2")){
            if (PhoneNumber.outCount==0){
                Log.d("Alert : "+ref_Number, "Doctor is OUT.");
                NotificationCompat.Builder mBuilder =
                        new NotificationCompat.Builder(context)
                                .setSmallIcon(R.drawable.notificationicon)
                                .setContentTitle("Alert : "+ref_Number)
                                .setContentText("Doctor is OUT.");
                NotificationManager mNotifyMgr =
                        (NotificationManager) context.getSystemService(android.content.Context.NOTIFICATION_SERVICE);
                Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                mBuilder.setSound(alarmSound);
                mBuilder.setVibrate(new long[] { 1000, 1000, 1000, 1000, 1000 });
                mBuilder.setLights(Color.RED, 3000, 3000);
                mNotifyMgr.notify(0, mBuilder.build());
                PhoneNumber.outCount++;
                PhoneNumber.cameCount=0;
            }
            else{
                Log.d("Alert : "+ref_Number, "Doctor is OUT. But NO NOTIFICATION.");
            }

        }
        else if (result.equals("0")) {
            Log.d("myTag", "NO NOTIFICATION");
        }
        else if (result.equals("-3")){
            Log.d("Alert : "+ref_Number, "Session finished - NO NOTIFICATION.");
            PhoneNumber.cameCount=0;
            PhoneNumber.outCount=0;
        }
        else if (result.equals(notify_Number)) {
            Log.d("Alert : "+ref_Number, "Appointment number "+result+" is now IN.");
            NotificationCompat.Builder mBuilder =
                    new NotificationCompat.Builder(context)
                            .setSmallIcon(R.drawable.notificationicon)
                            .setContentTitle("Alert : "+ref_Number)
                            .setContentText("Appointment number "+result+" is now IN.");
            NotificationManager mNotifyMgr =
                    (NotificationManager) context.getSystemService(android.content.Context.NOTIFICATION_SERVICE);
            Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            mBuilder.setSound(alarmSound);
            mBuilder.setVibrate(new long[] { 1000, 1000, 1000, 1000, 1000 });
            mBuilder.setLights(Color.RED, 3000, 3000);
            mNotifyMgr.notify(0, mBuilder.build());
            DatabaseHelper myDb = new DatabaseHelper(context);
            Log.d("MYTAG", "MESSAGE 1");
            myDb.deleteReqNo(ref_Number, notify_Number);
            Log.d("MYTAG", "MESSAGE 4");
            PhoneNumber.cameCount=0;
            PhoneNumber.outCount=0;
        }
        else if (Integer.parseInt(result)>Integer.parseInt(notify_Number)){
            Log.d("Alert Passed : "+ref_Number, "Appointment number "+result+" is now IN.");
            NotificationCompat.Builder mBuilder =
                    new NotificationCompat.Builder(context)
                            .setSmallIcon(R.drawable.notificationicon)
                            .setContentTitle("Alert : "+ref_Number)
                            .setStyle(new NotificationCompat.BigTextStyle()
                                    .bigText("Number "+result+" is now IN. "+"Number "+notify_Number+" is OVER now."))
                            .setContentText("Number "+result+" is now IN. "+"Number "+notify_Number+" is OVER now.");
            NotificationManager mNotifyMgr =
                    (NotificationManager) context.getSystemService(android.content.Context.NOTIFICATION_SERVICE);
            Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            mBuilder.setSound(alarmSound);
            mBuilder.setVibrate(new long[] { 1000, 1000, 1000, 1000, 1000 });
            mBuilder.setLights(Color.RED, 3000, 3000);
            mNotifyMgr.notify(0, mBuilder.build());
            DatabaseHelper myDb = new DatabaseHelper(context);
            Log.d("MYTAG", "MESSAGE 1");
            myDb.deleteReqNo(ref_Number, notify_Number);
            Log.d("MYTAG", "MESSAGE 4");
            PhoneNumber.cameCount=0;
            PhoneNumber.outCount=0;
        }
        else if (Integer.parseInt(result)<Integer.parseInt(notify_Number)){
            PhoneNumber.cameCount=0;
            PhoneNumber.outCount=0;
        }

    }


    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}
