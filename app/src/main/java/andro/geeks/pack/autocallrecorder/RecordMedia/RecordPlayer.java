package andro.geeks.pack.autocallrecorder.RecordMedia;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import andro.geeks.pack.autocallrecorder.R;

/**
 * Created by pallob on 4/3/18.
 */

public class RecordPlayer extends AppCompatActivity {

    TextView textView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent=getIntent();
        String passedString=intent.getStringExtra("Name");
        setContentView(R.layout.recordplayerinterface);
        getSupportActionBar().setTitle("");


    }
}
