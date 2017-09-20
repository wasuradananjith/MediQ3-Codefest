package com.example.wasuradananjith.mediq;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by Wasura Dananjith on 30-Jan-17.
 */

public class StartAtBootServiceReceiver extends BroadcastReceiver
{
    @Override
    public void onReceive(Context context, Intent intent)
    {
        if (intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED)) {
            Intent i = new Intent();
            i.setAction("com.example.wasuradananjith.mediq.Serv");
            context.startService(i);
        }
    }
}