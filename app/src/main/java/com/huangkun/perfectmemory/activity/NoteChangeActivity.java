package com.huangkun.perfectmemory.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;

import com.huangkun.perfectmemory.R;
import com.huangkun.perfectmemory.db.ModelDB;
import com.huangkun.perfectmemory.model.Note;
import com.huangkun.perfectmemory.utils.FormatTime;

import java.util.List;

/**
 * Created by hi on 2016/9/6.
 */
public class NoteChangeActivity extends Activity implements View.OnClickListener {

    private int position;
    private TextView timeTv;
    private EditText contentEt;
    private Button back, save;
    private List<Note> mNotes;
    private String contentLast;
    private String timeLast;

    private ModelDB modelDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.note_add);
        position = getIntent().getIntExtra("position", 0);
        initView();
        modelDB = ModelDB.getInstance(this);
        mNotes = modelDB.loadNote(); //获取数据列表
        Note note = mNotes.get(position);
        contentLast = note.getContent();
        timeLast = note.getTime();
        timeTv.setText(timeLast);
        contentEt.setText(contentLast);
        contentEt.setCursorVisible(false); //设置不显示光标和输入键盘，方便用户查看信息
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);//用户点击内容时弹出键盘
        contentEt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contentEt.setCursorVisible(true); //用户点击内容后，设置光标可见
            }
        });

    }

    private void initView() {
        timeTv = (TextView) findViewById(R.id.tv_note_add_time);
        contentEt = (EditText) findViewById(R.id.et_note_add_content);
        back = (Button) findViewById(R.id.btn_note_add_back);
        save = (Button) findViewById(R.id.btn_note_add_save);
        back.setOnClickListener(this);
        save.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_note_add_back:
                finish();
                break;
            case R.id.btn_note_add_save:
                String contentNew = contentEt.getText().toString();
                //如果保存时时。发现数据与之前不同，则删除之前的数据，保存现在的数据，否则不做改变
                if (!contentNew.equals(contentLast)) {
                    modelDB.deleteNote(timeLast);
                    Note note = new Note();
                    note.setTime(FormatTime.getCurrentTime());
                    note.setContent(contentNew);
                    modelDB.saveNote(note);
                }
                finish();
                break;
            default:
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        String contentNew = contentEt.getText().toString();
        if (!contentNew.equals(contentLast)) {
            modelDB.deleteNote(timeLast);
            Note note = new Note();
            note.setTime(FormatTime.getCurrentTime());
            note.setContent(contentNew);
            modelDB.saveNote(note);
        }
    }
}
