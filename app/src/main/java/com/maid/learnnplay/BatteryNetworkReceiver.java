package com.maid.learnnplay;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.BatteryManager;
import android.widget.Toast;

public class BatteryNetworkReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();

        if (action != null) {
            if (action.equals(Intent.ACTION_BATTERY_CHANGED)) {
                // Battery state changed
                int batteryLevel = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
                int batteryStatus = intent.getIntExtra(BatteryManager.EXTRA_STATUS, -1);

                Toast.makeText(context, "Battery Level: " + batteryLevel + "%", Toast.LENGTH_SHORT).show();
            } else if (action.equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
                boolean noConnectivity = intent.getBooleanExtra(ConnectivityManager.EXTRA_NO_CONNECTIVITY, false);

                if (noConnectivity) {
                    Toast.makeText(context, "Network Disconnected", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Network Connected", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}
