package com.huangkun.perfectmemory.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.huangkun.perfectmemory.R;

import java.io.BufferedReader;
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

        TextView explainTv = (TextView) findViewById(R.id.tv_explain_help_show);
        setText("explain.txt", explainTv);
        TextView moneyTv = (TextView) findViewById(R.id.tv_money_help_show);
        setText("money.txt", moneyTv);
        TextView alarmTv = (TextView) findViewById(R.id.tv_alarm_help_show);
        setText("alarm.txt", alarmTv);
        TextView sumMoneyTv = (TextView) findViewById(R.id.tv_sum_money_help_show);
        setText("sum.txt", sumMoneyTv);
        TextView noteTv = (TextView) findViewById(R.id.tv_note_help_show);
        setText("note.txt", noteTv);

    }
    public void setText(String path, TextView textView){
        try {
            InputStream is = getAssets().open(path);
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
