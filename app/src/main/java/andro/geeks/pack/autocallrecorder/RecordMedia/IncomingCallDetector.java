package andro.geeks.pack.autocallrecorder.RecordMedia;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.telephony.TelephonyManager;
import android.widget.Toast;

/**
 * Created by pallob on 4/3/18.
 */

public class IncomingCallDetector extends BroadcastReceiver {
    String APPSTATE;
    @Override
    public void onReceive(Context context, Intent intent) {

        DataBase dataBase=new DataBase(context);
        Cursor cursor=dataBase.getall();
        while (cursor.moveToNext()){

        APPSTATE=cursor.getString(0);
        }

        try {

            String state=intent.getStringExtra(TelephonyManager.EXTRA_STATE);
            if(state.equals(TelephonyManager.EXTRA_STATE_RINGING)){
                if(APPSTATE.equals("TRUE")){

                    Toast.makeText(context,"Incoming Call Detected Start Recording",Toast.LENGTH_LONG).show();

                }else {
                    Toast.makeText(context,"Enable Auto Recorder",Toast.LENGTH_LONG).show();

                }


            }


        }catch (Exception e){
            e.printStackTrace();

        }
    }
}
