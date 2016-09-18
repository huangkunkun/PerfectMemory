package com.huangkun.perfectmemory;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.huangkun.perfectmemory.activity.AllMoneyActivity;
import com.huangkun.perfectmemory.activity.HelpActivity;
import com.huangkun.perfectmemory.activity.NoteActivity;
import com.huangkun.perfectmemory.fragment.JokeFragment;
import com.huangkun.perfectmemory.fragment.TechnologyFragment;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import java.util.ArrayList;
import java.util.List;

import floatball.FloatBall;
import floatball.FloatBallMenu;

public class MainActivity extends AppCompatActivity {

    private FloatBall mFloatBall;
    private RadioGroup mRadioGroup;
    private ArrayList<Fragment> fragments; //用一个集合保存所有的fragment实例
    private int curPosition = 0; //保存当前显示的fragment在fragments中的序号

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initFragment();   //1473756914
        initView();
        floatingBall();  //设置悬浮小球
        setSlidingMenu();
        Log.d("time", System.currentTimeMillis() / 1000 + "");
    }

    private void initFragment() {
        fragments = new ArrayList<>();
        fragments.add(new TechnologyFragment());
        fragments.add(new JokeFragment());
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, fragments.get(curPosition))
                .commit();
    }

    /**
     * 根据传入的序号，获取对应的fragment实例，并同时更新当前显示
     */
    private void showFragment(int position) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragments.get(position))
                .commit();
        curPosition = position;
    }

    private void initView() {
        mRadioGroup = (RadioGroup) findViewById(R.id.rg_main_activity);
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_calendar:
                        showFragment(0);
                        break;
                    case R.id.rb_joke:
                        showFragment(1);
                        break;
                }
            }
        });
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
        final RadioButton note = (RadioButton) findViewById(R.id.rb_memorial_sliding_menu);
        Button exit = (Button) findViewById(R.id.btn_exit_sliding_menu);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_all_sliding_menu:
                        Intent intent1 = new Intent(MainActivity.this, AllMoneyActivity.class);
                        startActivity(intent1);
                        mFloatBall.hideFloatBall(); //查看记录时隐藏悬浮球
                        allMoney.setChecked(false);
                        break;
                    case R.id.rb_memorial_sliding_menu:
                        Intent intent3 = new Intent(MainActivity.this, NoteActivity.class);
                        startActivity(intent3);
                        mFloatBall.hideFloatBall(); //隐藏悬浮球
                        note.setChecked(false);
                        break;
                    case R.id.rb_help_slidingmenu:
                        Intent intent2 = new Intent(MainActivity.this, HelpActivity.class);
                        startActivity(intent2);
                        mFloatBall.hideFloatBall(); //隐藏悬浮球
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
