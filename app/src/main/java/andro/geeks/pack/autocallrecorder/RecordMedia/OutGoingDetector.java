package andro.geeks.pack.autocallrecorder.RecordMedia;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.telephony.TelephonyManager;
import android.widget.Toast;

/**
 * Created by pallob on 4/4/18.
 */

public class OutGoingDetector extends BroadcastReceiver {
    String APPSTATE="";
    String inputnumber;
    @Override
    public void onReceive(Context context, Intent intent) {

        DataBase dataBase=new DataBase(context);
        Cursor cursor=dataBase.getall();
        while (cursor.moveToNext()){

            APPSTATE=cursor.getString(0);
        }



        if(APPSTATE.equals("TRUE")){





        }else  {
            Toast.makeText(context,"Enable Auto Recorder",Toast.LENGTH_SHORT).show();

        }


    }
}
