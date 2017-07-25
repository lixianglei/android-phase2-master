package com.ge.med.mobile.nursing.scancode;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by gs on 2016/12/23.
 */
public class DataEditingPlugin extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (MyPrefs.HONEYWELL_ACTION_EDIT_DATA.equals(intent.getAction()))
        {
            String str6 = intent.getStringExtra(MyPrefs.HONEYWELL_ACTION_DATA_KEY);
            Log.d("DataEditingPlugin", "Barcode: " + str6);
            Intent localIntent2 = new Intent(MyPrefs.HONEYWELL_ACTION_SCAN_RESULT);
            localIntent2.putExtra("decode_rslt", str6);
            context.sendBroadcast(localIntent2);
        }
    }
}
