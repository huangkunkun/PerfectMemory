package com.huangkun.perfectmemory.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import com.huangkun.perfectmemory.R;
import com.huangkun.perfectmemory.db.ModelDB;
import com.huangkun.perfectmemory.fragment.MoneyContentFragment;
import com.huangkun.perfectmemory.model.Money;

import java.util.List;

/**
 * 用于控制消费信息显示中，用户左右滑动查看上、下一次的消费信息
 */
public class MoneyPagerActivity extends FragmentActivity {
    private ViewPager mViewPager;
    private List<Money> mMoneys;
    private int position = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewPager = new ViewPager(this);
        mViewPager.setId(R.id.viewPager);
        setContentView(mViewPager);

        Intent intent = getIntent();
        position = intent.getIntExtra("position", 0); //用户点击的列表项的position

        ModelDB modelDB = ModelDB.getInstance(this);
        mMoneys = modelDB.loadMoney();

        FragmentManager manager = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(manager) {
            @Override
            public Fragment getItem(int position) {
                return MoneyContentFragment.newInstance(position);
            }

            @Override
            public int getCount() {
                return mMoneys.size();
            }
        });

        //设置循环检查，使用户点击进入的刚好是所选择的内容，否则每次点击列表进入总会显示第一个
        for (int i = 0; i < mMoneys.size(); i ++){
            if( i == position){
                mViewPager.setCurrentItem(i);
                break;
            }
        }
    }
}
