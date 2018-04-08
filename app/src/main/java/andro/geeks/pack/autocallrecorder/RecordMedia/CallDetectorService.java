package andro.geeks.pack.autocallrecorder.RecordMedia;
import android.app.Service;
import android.content.Intent;
import android.database.Cursor;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by pallob on 4/5/18.
 */

public class CallDetectorService extends Service {
    Intent broadcast;
    DataBase dataBase;
    String APPSTATE="";
    @Override
    public void onCreate() {
        super.onCreate();
        broadcast = new Intent(CallDetectorService.this, BroadCastService.class);
        startService(broadcast);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {



        dataBase=new DataBase(this);

        final Handler handler=new Handler();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Cursor mycursor=dataBase.getall();
                while (mycursor.moveToNext()){
                    APPSTATE=mycursor.getString(0);
                    if (APPSTATE.equals("FALSE")){
                        stopService(broadcast);
                        stopSelf();
                    }

                }

                handler.postDelayed(this,1000);
            }
        },1000);


        startService(broadcast);
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        stopService(broadcast);
    }
}