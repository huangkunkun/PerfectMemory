package com.huangkun.perfectmemory.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.huangkun.perfectmemory.R;
import com.huangkun.perfectmemory.activity.MoneyPagerActivity;
import com.huangkun.perfectmemory.db.ModelDB;
import com.huangkun.perfectmemory.model.Money;

import java.util.List;

/**
 * Created by hi on 2016/8/30.
 */
public class MoneyTitleFragment extends ListFragment {

    private List<Money> mMoneys;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ModelDB modelDB = ModelDB.getInstance(getActivity());
        mMoneys = modelDB.loadMoney();
        MoneyAdapter adapter = new MoneyAdapter(getContext(), R.layout.money_title_item, mMoneys);
        setListAdapter(adapter);

    }

    private class MoneyAdapter extends ArrayAdapter<Money> {

        public MoneyAdapter(Context context, int resource, List<Money> objects) {
            super(context, resource, objects);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = getActivity().getLayoutInflater().inflate(R.layout.money_title_item, null);
            }
            TextView time = (TextView) convertView.findViewById(R.id.tv_money_title_time);
            TextView amount = (TextView) convertView.findViewById(R.id.tv_money_title_amount);
            Money m = getItem(position);
            time.setText(m.getMoneyTime());
            amount.setText(m.getMoneyAmount() + "");

            return convertView;
        }
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Intent intent = new Intent(getActivity(), MoneyPagerActivity.class);
        intent.putExtra("position", position);
        startActivity(intent);
    }

}
