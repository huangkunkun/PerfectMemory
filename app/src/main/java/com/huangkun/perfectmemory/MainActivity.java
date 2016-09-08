package com.huangkun.perfectmemory;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import com.huangkun.perfectmemory.fragment.TechnologyFragment;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import java.util.ArrayList;
import java.util.List;

import floatball.FloatBall;
import floatball.FloatBallMenu;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private List<View> viewList;
    private String[] title = {"科技", "英语", "读物", "新闻"};
    private FloatBall mFloatBall;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        floatingBall();  //设置悬浮小球
        setSlidingMenu();

        Fragment fragment = new TechnologyFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, fragment)
                .commit();
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
