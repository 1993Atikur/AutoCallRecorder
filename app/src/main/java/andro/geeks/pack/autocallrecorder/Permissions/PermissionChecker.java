package andro.geeks.pack.autocallrecorder.Permissions;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;

import android.content.pm.PackageManager;
import android.os.CountDownTimer;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import andro.geeks.pack.autocallrecorder.MainActivity;
import andro.geeks.pack.autocallrecorder.R;

import static android.Manifest.permission.READ_CALL_LOG;
import static android.Manifest.permission.READ_PHONE_STATE;
import static android.Manifest.permission.WRITE_CALL_LOG;
import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class PermissionChecker extends AppCompatActivity {

    public static final int RequestPermissionCode = 12;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);






        if (Build.VERSION.SDK_INT < 23) {

            CountDownTimer countDownTimer=new CountDownTimer(1300,1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    setContentView(R.layout.permissioninterface);


                }

                @Override
                public void onFinish() {
                    Intent intent1 = new Intent(PermissionChecker.this, MainActivity.class);
                    startActivity(intent1);
                    finish();

                }
            }.start();







        } else {
            setContentView(R.layout.permissioninterface);
            if (CheckingPermissionIsEnabledOrNot()) {
                Intent intent1 = new Intent(PermissionChecker.this, MainActivity.class);
              startActivity(intent1);
                finish();
            } else {
                RequestMultiplePermission();
            }


        }


    }

    private void RequestMultiplePermission() {

        ActivityCompat.requestPermissions(PermissionChecker.this, new String[]{
                READ_CALL_LOG, WRITE_CALL_LOG, READ_EXTERNAL_STORAGE, WRITE_EXTERNAL_STORAGE,READ_PHONE_STATE}, RequestPermissionCode);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {

            case RequestPermissionCode:

                if (grantResults.length > 0) {

                    boolean readcalllog = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    boolean writecallog = grantResults[1] == PackageManager.PERMISSION_GRANTED;
                    boolean readexternalstorage = grantResults[2] == PackageManager.PERMISSION_GRANTED;
                    boolean writeexternalstorage = grantResults[3] == PackageManager.PERMISSION_GRANTED;
                    boolean phonestate=grantResults[4]==PackageManager.PERMISSION_GRANTED;


                    if (readcalllog && writecallog && readexternalstorage && writeexternalstorage && phonestate) {

                        Intent intent1 = new Intent(PermissionChecker.this, MainActivity.class);
                        startActivity(intent1);
                        finish();

                    } else {

                        WarningDisplay();

                    }
                }

                break;
        }
    }

    public boolean CheckingPermissionIsEnabledOrNot() {

        int FirstPermissionResult = ContextCompat.checkSelfPermission(getApplicationContext(), READ_CALL_LOG);
        int SecondPermissionResult = ContextCompat.checkSelfPermission(getApplicationContext(), WRITE_CALL_LOG);
        int ThirdPermissionResult = ContextCompat.checkSelfPermission(getApplicationContext(), READ_EXTERNAL_STORAGE);
        int ForthPermissionResult = ContextCompat.checkSelfPermission(getApplicationContext(), WRITE_EXTERNAL_STORAGE);
        int Fiftpermission=ContextCompat.checkSelfPermission(getApplicationContext(),READ_PHONE_STATE);

        return FirstPermissionResult == PackageManager.PERMISSION_GRANTED &&
                SecondPermissionResult == PackageManager.PERMISSION_GRANTED &&
                ThirdPermissionResult == PackageManager.PERMISSION_GRANTED &&
                ForthPermissionResult == PackageManager.PERMISSION_GRANTED&&
                Fiftpermission==PackageManager.PERMISSION_GRANTED;
    }


    public void WarningDisplay(){

        Dialog dialog=new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.warningdialog);
        dialog.setCancelable(false);
        dialog.show();

        Button Exit=(Button)dialog.findViewById(R.id.exit);
        Button Start=(Button)dialog.findViewById(R.id.activitystart);

        Exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();

            }
        });

        Start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PermissionChecker.this,PermissionChecker.class));
                finish();

            }
        });


    }




}