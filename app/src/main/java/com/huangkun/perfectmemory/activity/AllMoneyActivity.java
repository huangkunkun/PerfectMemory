package com.huangkun.perfectmemory.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.huangkun.perfectmemory.R;
import com.huangkun.perfectmemory.db.MoneyDB;
import com.huangkun.perfectmemory.fragment.MoneyTitleFragment;
import com.huangkun.perfectmemory.model.Money;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by hi on 2016/8/31.
 */
public class AllMoneyActivity extends FragmentActivity {
    private List<Money> mMoneys;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.money_title_layout);

        TextView text = (TextView) findViewById(R.id.tv_money_text);
        TextView number = (TextView) findViewById(R.id.tv_money_number);
        MoneyDB moneyDB = MoneyDB.getInstance(this);
        mMoneys = moneyDB.loadMoney();
        if (mMoneys.size() != 0){
            int allNumber = 0;
            for (Money m : mMoneys){
                allNumber = allNumber + m.getMoneyAmount();
            }
            text.setText("共计");
            number.setText(allNumber +"");
        }

        FragmentManager manager = getSupportFragmentManager();
        Fragment fragment = manager.findFragmentById(R.id.money_container);
        if (fragment == null){
            fragment = new MoneyTitleFragment();
            manager.beginTransaction()
                    .add(R.id.money_container, fragment)
                    .commit();
        }
    }
}
