package com.huangkun.perfectmemory.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.huangkun.perfectmemory.R;
import com.huangkun.perfectmemory.db.ModelDB;
import com.huangkun.perfectmemory.model.Note;

import java.util.List;

/**
 * Created by hi on 2016/9/5.
 */
public class NoteActivity extends Activity implements View.OnClickListener {

    private List<Note> mNotes;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.note_layout);
        showData();
        Button addNote = (Button) findViewById(R.id.btn_note_add);
        Button back = (Button) findViewById(R.id.btn_note_back);
        addNote.setOnClickListener(this);
        back.setOnClickListener(this);
    }

    private void showData() {
        final ModelDB modelDB = ModelDB.getInstance(this);
        mNotes = modelDB.loadNote();
        if (mNotes.size() != 0) {
            listView = (ListView) findViewById(R.id.ll_note_show);
            final NoteAdapter adapter = new NoteAdapter(mNotes);
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(NoteActivity.this, NoteChangeActivity.class);
                    intent.putExtra("position", position);
                    startActivity(intent);
                }
            });
            listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                    final Note note = mNotes.get(position);
                    String title = note.getContent();
                    //判断内容的长度，如果超过8位，就截取前8位并加上省略号作为标题，否则不做不处理
                    if (title.length() >= 8){
                        title = title.substring(0, 8) + "...";
                    }
                    AlertDialog.Builder builder = new AlertDialog.Builder(NoteActivity.this);
                    builder.setTitle(title);
                    builder.setMessage("确定要删除该记录吗？");
                    builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            modelDB.deleteNote(note.getTime());
                            recreate(); //删除后要刷新当前界面显示的数据
                        }
                    });
                    builder.setNegativeButton("取消", null);
                    builder.show();

                    return true;
                }
            });
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_note_add:
                Intent intent1 = new Intent(NoteActivity.this, NoteAddActivity.class);
                startActivity(intent1);
                break;
            case R.id.btn_note_back:
                finish();
                break;
            default:
                break;
        }
    }

    private class NoteAdapter extends ArrayAdapter<Note> {
        public NoteAdapter(List<Note> objects) {
            super(getApplicationContext(), 0, objects);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.note_item, null);
            }
            Note note = getItem(position);
            TextView contentTv = (TextView) convertView.findViewById(R.id.tv_note_item_content);
            TextView timeTv = (TextView) convertView.findViewById(R.id.tv_note_item_time);
            contentTv.setText(note.getContent());
            timeTv.setText(note.getTime());

            return convertView;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        showData();
    }
}
