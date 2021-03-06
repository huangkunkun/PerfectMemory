package com.huangkun.perfectmemory.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import com.huangkun.perfectmemory.R;
import com.huangkun.perfectmemory.db.ModelDB;
import com.huangkun.perfectmemory.model.Money;

import java.util.List;

/**
 * 显示消费记录的详细信息
 */
public class MoneyContentFragment extends Fragment {

    private List<Money> mMoneys;
    private int position = 0;
    private EditText mean;
    private ModelDB modelDB;
    private String meanLast;
    private String timeLast;

    public static MoneyContentFragment newInstance(int position) {
        Bundle args = new Bundle();
        args.putInt("position", position); //将由MoneyPagerActivity传递过来的position放进去，在onCreate中获取
        MoneyContentFragment fragment = new MoneyContentFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         modelDB = ModelDB.getInstance(getActivity());
        mMoneys = modelDB.loadMoney();
        //Intent intent = getActivity().getIntent();
        //int index = intent.getIntExtra("position", 0);
        position = getArguments().getInt("position", 0); //获取传递的position，即用户滑动到下一个页面对应的position

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.money_content_item, null);
        Money m = mMoneys.get(position);
        meanLast = m.getMoneyMean();
        timeLast = m.getMoneyTime();
        TextView amount = (TextView) v.findViewById(R.id.money_content_amount);
        amount.setText(m.getMoneyAmount() + "");
        mean = (EditText) v.findViewById(R.id.money_content_mean);
        mean.setText(meanLast);
        mean.setCursorVisible(false);
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        mean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mean.setCursorVisible(true);
            }
        });
        TextView time = (TextView) v.findViewById(R.id.money_content_time);
        time.setText(timeLast);

        return v;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        String meanNew = mean.getText().toString();
        if (!meanNew.equals(meanLast)){
            modelDB.changeMoney(timeLast, meanNew);
        }
    }
}
