package com.huangkun.perfectmemory.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.huangkun.perfectmemory.R;
import com.huangkun.perfectmemory.activity.ChooseItemDetailActivity;
import com.huangkun.perfectmemory.model.Technology;
import com.huangkun.perfectmemory.utils.MyHttpUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hi on 2016/9/8.
 */
public class TechnologyFragment extends Fragment {
    private ListView mListView;
    private static String BASE_URL = " http://api.avatardata.cn/TechNews/Query?key=da55928d3c04432c91c4290a6f1ee4d6";
    private ArrayList<Technology> mTechnologies;
    private MyHandler handler = new MyHandler();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.techonlogy, null);
        mListView = (ListView) v.findViewById(R.id.ll_technology_show);

        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTechnologies = getData(); //获取到Technology的数据集
    }

    /**
     * 解析JSON数据，并将获取的数据保存到集合中
     *
     * @return Technology类的集合
     */
    private ArrayList<Technology> getData() {
        mTechnologies = new ArrayList<>();
        new Thread(new Runnable() {
            @Override
            public void run() {
                URL url = null;
                try {
                    url = new URL(BASE_URL);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                String data = MyHttpUtil.getTextFromUrl(url);
                try {
                    JSONObject rootObject = new JSONObject(data); //获得根对象
                    JSONArray jsonArray = rootObject.getJSONArray("result");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        Technology technology = new Technology();
                        technology.setTime(jsonObject.getString("ctime"));
                        technology.setBitmap(MyHttpUtil.getBitmapFromUrl(jsonObject.getString("picUrl")));
                        technology.setTitle(jsonObject.getString("title"));
                        technology.setUrl(jsonObject.getString("url"));
                        mTechnologies.add(technology);
                    }

                    Message message = Message.obtain();
                    handler.sendMessage(message);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        return mTechnologies;
    }

    /**
     * 获取数据结束后，进行处理，将适配器与listView进行绑定，显示在界面上
     */
    private class MyHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            LLAdapter adapter = new LLAdapter(getActivity(), R.layout.techonlogy, mTechnologies);
            mListView.setAdapter(adapter);
            mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(getActivity(), ChooseItemDetailActivity.class);
                    intent.putExtra("url",mTechnologies.get(position).getUrl());
                    startActivity(intent);
                }
            });
        }
    }

    /**
     * ListView的适配器类
     */
    private class LLAdapter extends ArrayAdapter<Technology> {

        public LLAdapter(Context context, int resource, List<Technology> objects) {
            super(context, resource, objects);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            Technology technology = getItem(position);

           if (convertView == null) {
                convertView = LayoutInflater.from(getActivity()).inflate(R.layout.technology_item, null);
                viewHolder = new ViewHolder();
                viewHolder.imageView = (ImageView) convertView.findViewById(R.id.iv_technology_bitmap_item);
                viewHolder.timeTv = (TextView) convertView.findViewById(R.id.tv_technology_time_item);
                viewHolder.titleTv = (TextView) convertView.findViewById(R.id.tv_technology_title_item);
                convertView.setTag(viewHolder);

            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            viewHolder.imageView.setImageBitmap(technology.getBitmap());
            viewHolder.titleTv.setText(technology.getTitle());
            viewHolder.timeTv.setText(technology.getTime());

            return convertView;
          }
        }
        class ViewHolder {
            ImageView imageView;
            TextView timeTv;
            TextView titleTv;
        }
    }
