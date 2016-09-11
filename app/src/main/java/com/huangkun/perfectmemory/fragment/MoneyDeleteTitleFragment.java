package com.huangkun.perfectmemory.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.huangkun.perfectmemory.R;
import com.huangkun.perfectmemory.db.ModelDB;
import com.huangkun.perfectmemory.model.Money;

import java.util.List;

/**
 * Created by huangkun on 2016/9/10.
 */
public class MoneyDeleteTitleFragment extends ListFragment {


    private List<Money> mMoneys;
    private ListMoneyInterface listMoney ;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ModelDB modelDB = ModelDB.getInstance(getActivity());
        mMoneys = modelDB.loadMoney();
        DeleteAdapter adapter = new DeleteAdapter(getActivity(), R.layout.money_delete_item, mMoneys);
        setListAdapter(adapter);
    }


    private class DeleteAdapter extends ArrayAdapter<Money> {

        public DeleteAdapter(Context context, int resource, List<Money> objects) {
            super(context, resource, objects);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            final Money money = getItem(position);
            final ViewHolder viewHolder;
            if (convertView == null) {
                viewHolder = new ViewHolder();
                convertView = LayoutInflater.from(getActivity()).inflate(R.layout.money_delete_item, null);
                viewHolder.amountTv = (TextView) convertView.findViewById(R.id.tv_money_delete_amount);
                viewHolder.timeTv = (TextView) convertView.findViewById(R.id.tv_money_delete_time);
                viewHolder.mCheckBox = (CheckBox) convertView.findViewById(R.id.cb_money_delete);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            viewHolder.amountTv.setText(money.getMoneyAmount() + "");
            viewHolder.timeTv.setText(money.getMoneyTime());
            viewHolder.mCheckBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (viewHolder.mCheckBox.isChecked()){
                        money.setChecked(true);
                    }
                }
            });
            listMoney.getList(mMoneys);

            return convertView;
        }
    }

    class ViewHolder {
        TextView amountTv;
        TextView timeTv;
        CheckBox mCheckBox;
    }

    /*
    fragment与activity产生关联时调用该方法，当前fragment从activity中重写回调接口，得到接口实例化对象
     */
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        listMoney = (ListMoneyInterface) getActivity();
    }



    public interface ListMoneyInterface {
         void getList(List<Money> list);
    }
}
