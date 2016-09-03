package com.huangkun.perfectmemory.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.huangkun.perfectmemory.R;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by hi on 2016/9/3.
 */
public class HelpActivity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.help_layout);

        TextView textView = (TextView) findViewById(R.id.tv_help_show);

        try {
            InputStream is = getAssets().open("help.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String line = "";
            String content = "";
            while((line = reader.readLine()) != null){
                content += line;
            }
            textView.setText(content);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
