package com.gavingao.sms;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by Administrator on 2016/10/10 0010.
 */

public class SmsBroadcast extends BroadcastReceiver {

    private final String ACTION = "android.provider.Telephony.SMS_RECEIVED";
    @Override
    public void onReceive(Context context, Intent intent) {

        if (ACTION.equals(intent.getAction())) {

        }
    }
}
