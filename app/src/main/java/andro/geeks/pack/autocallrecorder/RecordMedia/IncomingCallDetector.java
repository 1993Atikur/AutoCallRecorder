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

                    try{

                        String state=intent.getStringExtra(TelephonyManager.EXTRA_STATE);
                        inputnumber=intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);
                        if (state.equals(TelephonyManager.EXTRA_STATE_RINGING)){
                            if(inputnumber!=null)
                                Toast.makeText(context,"Incoming Call Detected Start Recording for :"+inputnumber,Toast.LENGTH_SHORT).show();

                        }else if(state.equals(TelephonyManager.EXTRA_STATE_OFFHOOK)){


                        }else {



                            if (inputnumber!=null)
                                Toast.makeText(context,"Call Ended  From else :"+inputnumber,Toast.LENGTH_SHORT).show();

                        }


                    }catch (Exception e){


                    }



                }else  {
                    Toast.makeText(context,"Enable Auto Recorder",Toast.LENGTH_LONG).show();

                }


            }

}
