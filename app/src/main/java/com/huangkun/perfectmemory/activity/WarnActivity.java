package com.huangkun.perfectmemory.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.widget.SeekBar;
import android.widget.TextView;

import com.huangkun.perfectmemory.R;

/**
 * Created by hi on 2016/8/19.
 */
public class WarnActivity extends AppCompatActivity {

    private TextView contentShow;
    private SeekBar seekBar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.warn);
        Intent intent = getIntent();
        String content = intent.getStringExtra("content");

        contentShow = (TextView) findViewById(R.id.tv_content);
        contentShow.setText(content);
        contentShow.setTextColor(Color.GREEN);
        contentShow.setTextSize(20);

        seekBar = (SeekBar) findViewById(R.id.seekBar);
        seekBar.setMax(100);
        seekBar.setProgress(50);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                if (seekBar.getProgress() == 100) {
                    finish();
                }
            }
        });

    }
}
