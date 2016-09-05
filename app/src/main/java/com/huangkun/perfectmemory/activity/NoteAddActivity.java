package com.huangkun.perfectmemory.activity;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.huangkun.perfectmemory.R;
import com.huangkun.perfectmemory.db.ModelDB;
import com.huangkun.perfectmemory.model.Note;
import com.huangkun.perfectmemory.utils.FormatTime;

/**
 * Created by hi on 2016/9/5.
 */
public class NoteAddActivity extends Activity implements View.OnClickListener {
    private TextView timeAdd;
    private EditText contentAdd;
    private Button back;
    private Button save;
    private String content;
    private String time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.note_add);

        initView();

        time = FormatTime.getCurrentTime();
        timeAdd.setText(time);
        back.setOnClickListener(this);
        save.setOnClickListener(this);

    }

    private void initView() {
        timeAdd = (TextView) findViewById(R.id.tv_note_time);
        contentAdd = (EditText) findViewById(R.id.et_note_content);
        back = (Button) findViewById(R.id.btn_note_add_back);
        save = (Button) findViewById(R.id.btn_note_add_save);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_note_add_back:
                finish();
                break;
            case R.id.btn_note_add_save:
                content = contentAdd.getText().toString();
                if (TextUtils.isEmpty(content)){
                }else{
                    Note note = new Note();
                    note.setContent(content);
                    note.setTime(time);
                    ModelDB modelDB = ModelDB.getInstance(this);
                    modelDB.saveNote(note);
                    content = null;
                }
                finish();
                break;
        }
    }

    //如果用户输入内容之后，忘记保存，而是返回了，那么会自动将数据保存到数据库
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        content = contentAdd.getText().toString();
        if (!TextUtils.isEmpty(content)){
            Note note = new Note();
            note.setContent(content);
            note.setTime(time);
            ModelDB modelDB = ModelDB.getInstance(getApplicationContext());
            modelDB.saveNote(note);
        }
    }

    /*
    //如果用户输入内容之后，因为其他原因使该APP暂时停止或者销毁了，那么会自动将数据保存到数据库
    @Override
    protected void onPause() {
        super.onPause();
        content = contentAdd.getText().toString();
        if (!TextUtils.isEmpty(content)){
            Note note = new Note();
            note.setContent(content);
            note.setTime(time);
            ModelDB modelDB = ModelDB.getInstance(getApplicationContext());
            modelDB.saveNote(note);
        }
    }*/
}
