package andro.geeks.pack.autocallrecorder.RecordMedia;
import android.app.Service;
import android.content.Intent;
import android.media.MediaRecorder;
import android.os.Environment;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import static android.widget.Toast.LENGTH_SHORT;

/**
 * Created by pallob on 4/7/18.
 */

public class RecordingService extends Service {
    public   MediaRecorder recorder;
    @Override
    public void onCreate() {
        super.onCreate();
        Toast.makeText(getApplicationContext(),"Recording Started..",LENGTH_SHORT).show();
        StartRecording();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(getApplicationContext(),"Recording Stopped",LENGTH_SHORT).show();
        StopRecording();
        stopSelf();
    }


    private void StartRecording(){

        try {
            recorder = new MediaRecorder();

            recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            recorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
            recorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);
            Date date = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyyhhmmssaa");
            String currentDate = dateFormat.format(date);
            String path = Environment.getExternalStorageDirectory().getAbsolutePath();

            File file = new File(path + File.separator + "AutoCallRecorder");
            if(!file.exists()){
                file.mkdirs();
            }
            File recordObject = new File(file.getAbsolutePath() + File.separator + currentDate+ "recordFile.mp4");
            if(!recordObject.exists()){
                recordObject.createNewFile();
            }
            recorder.setOutputFile(recordObject.getAbsolutePath());
            recorder.prepare();
            recorder.start();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void StopRecording() {
        if (recorder != null) {
            recorder.stop();
            recorder.release();
            recorder = null;
        }
    }
}
