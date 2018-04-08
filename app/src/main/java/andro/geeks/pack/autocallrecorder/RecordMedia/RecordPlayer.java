package andro.geeks.pack.autocallrecorder.RecordMedia;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

import andro.geeks.pack.autocallrecorder.R;

/**
 * Created by pallob on 4/3/18.
 */

public class RecordPlayer extends AppCompatActivity {


    boolean state=true;
    boolean stopper=false;
    TextView textView,Directory,fileNAME;
    SeekBar seekBar;
    Button Controler;
    String path;
    String fileName;
    MediaPlayer player;
    Handler seekHandler = new Handler();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        Intent intent = getIntent();
        fileName = intent.getStringExtra("Name");

         path= Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "AutoCallRecorder" + File.separator + fileName;
        getSupportActionBar().setTitle("");
        File file = new File(path);

        if (file.exists()) {
            setContentView(R.layout.recordplayerinterface);
            Controler=(Button)findViewById(R.id.controler);
            textView=(TextView)findViewById(R.id.text_shown);
            Directory=(TextView)findViewById(R.id.directory);
            fileNAME=(TextView)findViewById(R.id.FILENAME);
            seekBar=(SeekBar)findViewById(R.id.seek_bar);
            getSupportActionBar().setElevation(0);
            Directory.setText("Directory: "+Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "AutoCallRecorder" + File.separator);
            fileNAME.setText("File Name: "+fileName);

            getInit();
            seekUpdation();
            Controler.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(state){
                        Controler.setBackgroundResource(R.drawable.pause);
                        textView.setText("Playing");
                        player.start();
                        state=false;
                        stopper=true;

                    }else {

                        Controler.setBackgroundResource(R.drawable.play);
                        state=true;
                        textView.setText("Paused");
                        player.pause();

                    }


                }
            });

            seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    if(fromUser)
                        player.seekTo(progress);
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }
            });





        } else {
           setContentView(R.layout.idle);

        }



    }

    public void getInit() {
        seekBar=(SeekBar)findViewById(R.id.seek_bar);
        player= MediaPlayer.create(RecordPlayer.this,  Uri.parse(Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "AutoCallRecorder" + File.separator + fileName));
        seekBar.setMax(player.getDuration());
    }

    Runnable run = new Runnable() {
        @Override
        public void run() {
            seekUpdation();
        }
    };

    public void seekUpdation() {
        seekBar=(SeekBar)findViewById(R.id.seek_bar);
        seekBar.setProgress(player.getCurrentPosition());
        seekHandler.postDelayed(run, 1000);
    }

    @Override
    public void onBackPressed() {

        if(stopper){
            player.stop();
        }
        super.onBackPressed();
        finish();
    }
}
