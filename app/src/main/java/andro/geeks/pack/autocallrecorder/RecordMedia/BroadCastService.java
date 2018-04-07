package andro.geeks.pack.autocallrecorder.RecordMedia;

/**
 * Created by pallob on 4/8/18.
 */

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.telephony.TelephonyManager;

/**
 * Created by pallob on 4/5/18.
 */

public class BroadCastService extends Service {
    IntentFilter intentFilter;
    Intent intent1, intent2;
    String type = null;
    int i = 0;


    BroadcastReceiver receiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {

            String State = intent.getStringExtra(TelephonyManager.EXTRA_STATE);

            try {
                if (intent.getAction().equals(Intent.ACTION_NEW_OUTGOING_CALL)) {

                    type = "Outgoing ";
                    startService(intent2);

                } else if (State.equals(TelephonyManager.EXTRA_STATE_RINGING)) {

                    type = "Incoming ";
                    startService(intent1);

                } else if (State.equals(TelephonyManager.EXTRA_STATE_OFFHOOK)) {

                } else if (State.equals(TelephonyManager.EXTRA_STATE_IDLE)) {
                    if (i <= 0) {
                        if (type.equals("Outgoing")) {
                            stopService(intent2);
                        } else {
                            stopService(intent1);
                        }

                        i++;

                    } else {
                        stopService(intent2);

                    }

                }

            } catch (Exception e) {

            }
        }


    };


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        intent1 = new Intent(BroadCastService.this, RecordingService.class);
        intent2 = new Intent(BroadCastService.this, RecordingService.class);
        intentFilter = new IntentFilter();
        intentFilter.addAction(TelephonyManager.ACTION_PHONE_STATE_CHANGED);
        intentFilter.addAction(Intent.ACTION_NEW_OUTGOING_CALL);
        registerReceiver(receiver, intentFilter);


    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        registerReceiver(receiver, intentFilter);
        intent1 = new Intent(BroadCastService.this, RecordingService.class);
        intent2 = new Intent(BroadCastService.this, RecordingService.class);

        return super.onStartCommand(intent, flags, startId);
    }


}
