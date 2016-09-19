package com.huangkun.perfectmemory.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.huangkun.perfectmemory.R;
import com.huangkun.perfectmemory.model.Joke;
import com.huangkun.perfectmemory.utils.MyHttpUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by huangkun on 2016/9/12.
 */
public class JokeFragment extends Fragment {

    private ListView mListView;
    private static String BASE_URL = " http://api.avatardata.cn/Joke/QueryJokeByTime?key=23f7edb3dcb84be7883ab080adbb6576";
    private static String SORT = "&sort=desc";
    private static String ROWS = "&rows=10";
    private String time = "&time=" + System.currentTimeMillis() / 1000; //设置当前时间的unix时间戳
    private ArrayList<Joke> mJokes;

    private MyHandler mMyHandler = new MyHandler();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.joke, null);
        mListView = (ListView) v.findViewById(R.id.ll_joke_show);

        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mJokes = getData();
    }

    private ArrayList<Joke> getData() {
        mJokes = new ArrayList<>();
        new Thread(new Runnable() {
            @Override
            public void run() {
                URL url = null;
                try {
                    url = new URL(BASE_URL + ROWS + SORT + time);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                String data = MyHttpUtil.getTextFromUrl(url);
                try {
                    JSONObject rootObject = new JSONObject(data);
                    JSONArray jsonArray = rootObject.getJSONArray("result");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        Joke joke = new Joke();
                        joke.setContent(jsonObject.getString("content"));
                        mJokes.add(joke);
                    }
                    Message message = Message.obtain();
                    mMyHandler.sendMessage(message);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        return mJokes;
    }

    private class MyHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            JokeAdapter adapter = new JokeAdapter(getActivity(), R.layout.joke_item, mJokes);
            mListView.setAdapter(adapter);
            mListView.setEmptyView(getActivity().findViewById(R.id.iv_technology_empty_ll));
        }
    }

    private class JokeAdapter extends ArrayAdapter<Joke> {

        public JokeAdapter(Context context, int resource, List<Joke> objects) {
            super(context, resource, objects);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if (convertView == null) {
                convertView = LayoutInflater.from(getActivity()).inflate(R.layout.joke_item, null);
                viewHolder = new ViewHolder();
                viewHolder.mTextView = (TextView) convertView.findViewById(R.id.tv_joke_item);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            Joke joke = getItem(position);
            viewHolder.mTextView.setText(joke.getContent());

            return convertView;
        }
    }

    class ViewHolder {
        TextView mTextView;
    }
}
