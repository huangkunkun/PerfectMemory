package com.huangkun.perfectmemory.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.Button;

import com.huangkun.perfectmemory.R;
import com.huangkun.perfectmemory.db.ModelDB;
import com.huangkun.perfectmemory.fragment.MoneyDeleteTitleFragment;
import com.huangkun.perfectmemory.model.Money;

import java.util.List;

/**
 * Created by huangkun on 2016/9/10.
 */
public class MoneyDeleteActivity extends FragmentActivity implements View.OnClickListener, MoneyDeleteTitleFragment.ListMoneyInterface {

    private Button back;
    private Button delete;
    private List<Money> mMoneys;
    private Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.money_delete_layout);

        back = (Button) findViewById(R.id.btn_money_delete_back);
        delete = (Button) findViewById(R.id.btn_money_delete_delete);
        back.setOnClickListener(this);
        delete.setOnClickListener(this);

        FragmentManager manager = getSupportFragmentManager();
        fragment = manager.findFragmentById(R.id.money_delete_container);
        if (fragment == null) {
            fragment = new MoneyDeleteTitleFragment();
            manager.beginTransaction()
                    .add(R.id.money_delete_container, fragment)
                    .commit();

        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_money_delete_back:
                finish();
                break;
            case R.id.btn_money_delete_delete:
                ModelDB modelDB = ModelDB.getInstance(this);
                for (Money m : mMoneys){
                    if (m.isChecked()){
                        modelDB.deleteMoney(m.getMoneyTime());
                    }
                }
                startActivity(new Intent(MoneyDeleteActivity.this, AllMoneyActivity.class));
                finish();
                break;
        }
    }

    @Override
    public void getList(List<Money> list) {
        this.mMoneys = list;
    }
}
