package me.chrisgavin.notifydump;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.google.gson.Gson;

public class NotifyDumpReceiver extends BroadcastReceiver {
    private static final Gson GSON = new Gson();

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(NotifyDumpReceiver.class.getSimpleName(), "Received intent with action " + intent.getAction() + ".");
        NotifyDumpService notifyDumpService = NotifyDumpService.getInstance();
        if (notifyDumpService == null) {
            setResultCode(2);
            setResultData(NotifyDumpService.class.getCanonicalName() + " is not running.");
            return;
        }
        setResultCode(Activity.RESULT_OK);
        setResultData(GSON.toJson(notifyDumpService.getNotifications()));
    }
}
