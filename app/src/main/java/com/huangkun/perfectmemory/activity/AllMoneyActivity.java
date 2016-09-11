package com.huangkun.perfectmemory.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.huangkun.perfectmemory.R;
import com.huangkun.perfectmemory.db.ModelDB;
import com.huangkun.perfectmemory.fragment.MoneyTitleFragment;
import com.huangkun.perfectmemory.model.Money;

import java.util.List;

/**
 * Created by hi on 2016/8/31.
 */
public class AllMoneyActivity extends FragmentActivity implements View.OnClickListener {
    private List<Money> mMoneys;
    private Fragment fragment;
    private FragmentManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.money_title_layout);

        TextView text = (TextView) findViewById(R.id.tv_money_text);
        TextView number = (TextView) findViewById(R.id.tv_money_number);
        Button back = (Button) findViewById(R.id.btn_money_back);
        Button delete = (Button) findViewById(R.id.btn_money_delete);
        back.setOnClickListener(this);
        delete.setOnClickListener(this);
        ModelDB modelDB = ModelDB.getInstance(this);
        mMoneys = modelDB.loadMoney();
        if (mMoneys.size() != 0) {
            double allNumber = 0;
            for (Money m : mMoneys) {
                allNumber = allNumber + m.getMoneyAmount();
            }
            text.setText("共计");
            number.setText(allNumber + "");
        }
        setFragment();
    }

    private void setFragment() {
        manager = getSupportFragmentManager();
        fragment = manager.findFragmentById(R.id.money_container);
        if (fragment == null) {
            fragment = new MoneyTitleFragment();
            manager.beginTransaction()
                    .add(R.id.money_container, fragment)
                    .commit();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_money_back:
                finish();
                break;
            case R.id.btn_money_delete:
                Intent intent = new Intent(AllMoneyActivity.this, MoneyDeleteActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }

}
