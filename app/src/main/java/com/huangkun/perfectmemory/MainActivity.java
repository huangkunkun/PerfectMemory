package com.huangkun.perfectmemory;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.huangkun.perfectmemory.activity.AllMoneyActivity;
import com.huangkun.perfectmemory.activity.HelpActivity;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import java.util.ArrayList;
import java.util.List;

import floatball.FloatBall;
import floatball.FloatBallMenu;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private List<View> viewList;
    private String[] title = {"日常", "英语", "读物", "新闻"};
    private FloatBall mFloatBall;

    public final Context CONTEXT = MainActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();  //初始化底部的几个View,及初始化控件
        initPagerAdapter(); //初始化ViewPager的适配器及viewPager绑定适配器
        floatingBall();  //设置悬浮小球
        setSlidingMenu();
    }

    private void setSlidingMenu() {
        SlidingMenu slidingMenu = new SlidingMenu(this);
        slidingMenu.setMode(SlidingMenu.LEFT);
        slidingMenu.setBehindOffset(300);
        slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
        slidingMenu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
        slidingMenu.setMenu(R.layout.sliding_menu);

        final RadioGroup radioGroup = (RadioGroup) findViewById(R.id.rg_sliding_menu);
        final RadioButton allMoney = (RadioButton) findViewById(R.id.rb_all_sliding_menu);
        final RadioButton help = (RadioButton) findViewById(R.id.rb_help_slidingmenu);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_all_sliding_menu:
                        Intent intent1 = new Intent(MainActivity.this, AllMoneyActivity.class);
                        startActivity(intent1);
                        allMoney.setChecked(false);
                        break;
                    case R.id.rb_help_slidingmenu:
                        Intent intent2 = new Intent(MainActivity.this, HelpActivity.class);
                        startActivity(intent2);
                        help.setChecked(false);
                        break;

                }
            }
        });
    }

    private void floatingBall() {
        FloatBallMenu menu = new FloatBallMenu(MainActivity.this);
        FloatBall.SingleIcon singleIcon = new FloatBall.SingleIcon(R.drawable.floatball, 1f, 0.3f);
        mFloatBall = new FloatBall.Builder(getApplicationContext()).menu(menu).icon(singleIcon).build();
        mFloatBall.setLayoutGravity(Gravity.RIGHT | Gravity.CENTER_VERTICAL);
    }


    private void initPagerAdapter() {
        PagerAdapter adapter = new PagerAdapter() {
            @Override
            public int getCount() {
                return viewList.size();
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                container.addView(viewList.get(position));
                return viewList.get(position);
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView(viewList.get(position));
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return title[position];
            }
        };
        viewPager.setAdapter(adapter);
    }

    private void initView() {
        viewList = new ArrayList<>();
        viewList.add(getView(R.layout.daily));
        viewList.add(getView(R.layout.english));
        viewList.add(getView(R.layout.reader));
        viewList.add(getView(R.layout.news));

        viewPager = (ViewPager) findViewById(R.id.vp_show);
    }

    private View getView(int resourceId) {
        View view = LayoutInflater.from(this).inflate(resourceId, null);
        return view;
    }

    @Override
    protected void onStart() {
        super.onStart();
        mFloatBall.show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mFloatBall.dismiss();
    }
}
