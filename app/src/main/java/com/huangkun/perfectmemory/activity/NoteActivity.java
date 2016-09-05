package com.huangkun.perfectmemory.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
        Button deleteNote = (Button) findViewById(R.id.btn_note_delete);
        addNote.setOnClickListener(this);
        deleteNote.setOnClickListener(this);
    }

    private void showData() {
        ModelDB modelDB = ModelDB.getInstance(this);
        mNotes = modelDB.loadNote();
        if (mNotes.size() != 0) {
            listView = (ListView) findViewById(R.id.ll_note_show);
            listView.setAdapter(new NoteAdapter(mNotes));
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_note_add:
                Intent intent1 = new Intent(NoteActivity.this, NoteAddActivity.class);
                startActivity(intent1);
                break;
            case R.id.btn_note_delete:
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
            if (convertView == null){
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
