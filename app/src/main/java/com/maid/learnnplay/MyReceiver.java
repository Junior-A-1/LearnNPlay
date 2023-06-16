package com.maid.learnnplay;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        // This method will be called when the broadcast is received
        Toast.makeText(context, "Broadcast Receiver received", Toast.LENGTH_SHORT).show();
        // Add your desired functionality here
    }
}

