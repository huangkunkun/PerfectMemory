package floatball;

import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.huangkun.perfectmemory.R;
import com.huangkun.perfectmemory.db.MoneyDB;
import com.huangkun.perfectmemory.model.Money;
import com.huangkun.perfectmemory.receiver.AlarmReceiver;
import com.huangkun.perfectmemory.utils.FormatTime;
import com.huangkun.perfectmemory.utils.MyAlarm;
import com.huangkun.perfectmemory.utils.MyApplication;

import java.util.Calendar;

import floatball.interfaces.IMenu;


public class FloatBallMenu implements IMenu {
    private TextView tvLeftCenter, tvRightCenter, tvLeftGift, tvRightGift;
    private View leftLine, rightLine;
    private int menuWidth, menuHeight;
    private FloatBall mFloatBall;

    private Context mContext;
    private MyClickListener listener = new MyClickListener();

    public FloatBallMenu(Context context) {
        this.mContext = context;
    }

    @Override
    public void onAttach(FloatBall floatBall, Context context) {
        menuWidth = DensityUtil.dip2px(context, 135);
        menuHeight = DensityUtil.dip2px(context, 30);
        this.mFloatBall = floatBall;
    }

    @Override
    public void addMenu(RelativeLayout parent) {
        //设置菜单的背景色
        parent.setBackgroundColor(Color.parseColor("#fafafa"));

        addLeftMenu(parent);
        addRightMenu(parent);

        //默认状态下隐藏菜单
        showLeft(false);
        showRight(false);
    }

    private void addLeftMenu(RelativeLayout parent) {
        RelativeLayout.LayoutParams childLayoutParams = new RelativeLayout.LayoutParams(DensityUtil.dip2px(parent.getContext(), 52), DensityUtil.dip2px(parent.getContext(), 30));
        childLayoutParams.setMargins(0, 0, DensityUtil.dip2px(parent.getContext(), 20), 0);
        childLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        tvLeftGift = new TextView(parent.getContext());
        tvLeftGift.setId(getId());
        tvLeftGift.setText("事件");
        tvLeftGift.setOnClickListener(listener);
        tvLeftGift.setTextSize(14);
        tvLeftGift.setGravity(Gravity.CENTER);
        tvLeftGift.setTextColor(Color.parseColor("#333333"));
        parent.addView(tvLeftGift, childLayoutParams);
        childLayoutParams = new RelativeLayout.LayoutParams(DensityUtil.dip2px(parent.getContext(), 1), DensityUtil.dip2px(parent.getContext(), 30));
        childLayoutParams.setMargins(0, DensityUtil.dip2px(parent.getContext(), 8), 0, DensityUtil.dip2px(parent.getContext(), 8));
        childLayoutParams.addRule(RelativeLayout.LEFT_OF, tvLeftGift.getId());
        leftLine = new View(parent.getContext());
        leftLine.setId(getId());
        leftLine.setBackgroundColor(Color.parseColor("#e5e5e5"));
        parent.addView(leftLine, childLayoutParams);

        childLayoutParams = new RelativeLayout.LayoutParams(DensityUtil.dip2px(parent.getContext(), 52), DensityUtil.dip2px(parent.getContext(), 30));
        childLayoutParams.addRule(RelativeLayout.LEFT_OF, leftLine.getId());
        tvLeftCenter = new TextView(parent.getContext());
        tvLeftCenter.setId(getId());
        tvLeftCenter.setText("消费");
        tvLeftCenter.setOnClickListener(listener);
        tvLeftCenter.setTextSize(14);
        tvLeftCenter.setGravity(Gravity.CENTER);
        tvLeftCenter.setTextColor(Color.parseColor("#333333"));
        parent.addView(tvLeftCenter, childLayoutParams);
    }


    private void addRightMenu(RelativeLayout parent) {
        RelativeLayout.LayoutParams childLayoutParams = new RelativeLayout.LayoutParams(DensityUtil.dip2px(parent.getContext(), 52), DensityUtil.dip2px(parent.getContext(), 30));
        childLayoutParams.setMargins(DensityUtil.dip2px(parent.getContext(), 20), 0, 0, 0);
        tvRightCenter = new TextView(parent.getContext());
        tvRightCenter.setId(getId());
        tvRightCenter.setText("消费");
        tvRightCenter.setOnClickListener(listener);
        tvRightCenter.setTextSize(14);
        tvRightCenter.setGravity(Gravity.CENTER);
        tvRightCenter.setTextColor(Color.parseColor("#333333"));
        parent.addView(tvRightCenter, childLayoutParams);

        childLayoutParams = new RelativeLayout.LayoutParams(DensityUtil.dip2px(parent.getContext(), 1), DensityUtil.dip2px(parent.getContext(), 30));
        childLayoutParams.setMargins(0, DensityUtil.dip2px(parent.getContext(), 8), 0, DensityUtil.dip2px(parent.getContext(), 8));
        childLayoutParams.addRule(RelativeLayout.RIGHT_OF, tvRightCenter.getId());
        rightLine = new View(parent.getContext());
        rightLine.setId(getId());
        rightLine.setBackgroundColor(Color.parseColor("#e5e5e5"));
        parent.addView(rightLine, childLayoutParams);

        childLayoutParams = new RelativeLayout.LayoutParams(DensityUtil.dip2px(parent.getContext(), 52), DensityUtil.dip2px(parent.getContext(), 30));
        childLayoutParams.addRule(RelativeLayout.RIGHT_OF, rightLine.getId());
        tvRightGift = new TextView(parent.getContext());
        tvRightGift.setId(getId());
        tvRightGift.setText("事件");
        tvRightGift.setOnClickListener(listener);
        tvRightGift.setTextSize(14);
        tvRightGift.setGravity(Gravity.CENTER);
        tvRightGift.setTextColor(Color.parseColor("#333333"));
        parent.addView(tvRightGift, childLayoutParams);
    }

    @Override
    public boolean isRightMenuEnable() {
        return true;
    }

    @Override
    public boolean isLeftMenuEnable() {
        return true;
    }

    @Override
    public void showingRightMenu() {
        showRight(false);
        showLeft(true);
    }

    @Override
    public void showingLeftMenu() {
        showRight(true);
        showLeft(false);
    }

    @Override
    public int getMenuHeight() {
        return menuHeight;
    }

    @Override
    public int getMenuWidth() {
        return menuWidth;
    }

    private void showRight(boolean show) {
        tvLeftCenter.setVisibility(show ? View.VISIBLE : View.INVISIBLE);
        tvLeftGift.setVisibility(show ? View.VISIBLE : View.INVISIBLE);
        leftLine.setVisibility(show ? View.VISIBLE : View.INVISIBLE);
    }

    private void showLeft(boolean show) {
        tvRightCenter.setVisibility(show ? View.VISIBLE : View.INVISIBLE);
        tvRightGift.setVisibility(show ? View.VISIBLE : View.INVISIBLE);
        rightLine.setVisibility(show ? View.VISIBLE : View.INVISIBLE);
    }

    private int getId() {
        return IDFactory.getId();
    }

    private class MyClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            if (v == tvLeftCenter || v == tvRightCenter) {
                setMoneyDialog();
            } else if (v == tvLeftGift || v == tvRightGift) {
                setEventDialog();
            }
        }
    }

    private String inputAmount = "";
    private String inputMean = "";
    private EditText editMean;
    private EditText editAmount;

    //弹出记录消费的对话框
    private void setMoneyDialog() {
        getCurrentTime();
        final String timeStr = FormatTime.formatTime(yearGet, monthGet, dayGet, hourGet, minuteGet);
        View view = LayoutInflater.from(MyApplication.getContext()).inflate(R.layout.dialog_money, null);
        editMean = (EditText) view.findViewById(R.id.et_mean);
        editAmount = (EditText) view.findViewById(R.id.et_word);
        final AlertDialog dialog = new AlertDialog.Builder(mContext).setPositiveButton("确定", null)
                .setNegativeButton("取消", null).create();
        dialog.setView(view);
        dialog.setCancelable(true);
        dialog.show();
        dialog.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputAmount = editAmount.getText().toString();
                    inputMean = editMean.getText().toString();
                    if (TextUtils.isEmpty(inputAmount) || TextUtils.isEmpty(inputMean) ) {
                        Toast.makeText(mContext, "金额或用途未输入", Toast.LENGTH_SHORT).show();
                    } else if (String.valueOf(inputAmount.charAt(0)).equals(".") || (String.valueOf(inputAmount.charAt(0)).equals("0") && !inputAmount.contains("."))) {
                        Toast.makeText(mContext, "金额输入不合法", Toast.LENGTH_SHORT).show();
                    } else {
                        saveMoneyToSQL(Integer.parseInt(inputAmount), inputMean, timeStr); //将内容保存到数据库
                        saveNumberMoney(); //保存消费的序号到本地
                        Toast.makeText(mContext, "保存成功", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                }
            }

            );
            mFloatBall.hideMenu();
        }

    private void saveMoneyToSQL(int inputAmount, String inputMean, String timeStr) {
        Money money = new Money();
        money.setMoneyAmount(inputAmount);
        money.setMoneyMean(inputMean);
        money.setMoneyTime(timeStr);
        MoneyDB moneyDB = MoneyDB.getInstance(mContext);
        moneyDB.saveMoney(money);
    }

    private String inputEvent = "";
    private int yearGet = 0;
    private int monthGet = 0;
    private int dayGet = 0;
    private int hourGet = 0;
    private int minuteGet = 0;
    private EditText editEvent;

    //弹出记录事件的对话框
    private void setEventDialog() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_event, null);
        editEvent = (EditText) view.findViewById(R.id.et_event);
        getCurrentTime(); //初始化为当前系统日期时间，如果用户没有选择，则默认为当前时间
        DatePicker datePicker = (DatePicker) view.findViewById(R.id.dp_show);
        datePicker.init(yearGet, monthGet, dayGet, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                yearGet = year;
                monthGet = monthOfYear;
                dayGet = dayOfMonth;
            }
        });

        TimePicker timePicker = (TimePicker) view.findViewById(R.id.tp_show);
        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                hourGet = hourOfDay;
                minuteGet = minute;
            }
        });
        final AlertDialog dialog = new AlertDialog.Builder(mContext).setPositiveButton("确定", null)
                .setNegativeButton("取消", null).create();
        dialog.setView(view);
        dialog.show();
        dialog.setCancelable(true);
        dialog.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputEvent = editEvent.getText().toString();
                if (TextUtils.isEmpty(inputEvent)) {
                    Toast.makeText(mContext, "事件内容未输入", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    saveEventData(); //保存数据到本地
                    setAlarm();  //设置定时提醒
                    Toast.makeText(mContext, "保存成功", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
            }
        });
        mFloatBall.hideMenu();
    }

    //设置定时提醒
    private void setAlarm() {
        long time = FormatTime.getTime(yearGet, monthGet, dayGet, hourGet, minuteGet);
        MyAlarm myAlarm = new MyAlarm();
        myAlarm.setAlarm(mContext, AlarmReceiver.class, time, inputEvent);
    }

    //获取当前时间
    private void getCurrentTime() {
        Calendar calendar = Calendar.getInstance();
        yearGet = calendar.get(Calendar.YEAR);
        monthGet = calendar.get(Calendar.MONTH);
        dayGet = calendar.get(Calendar.DAY_OF_MONTH);
        hourGet = calendar.get(Calendar.HOUR_OF_DAY);
        minuteGet = calendar.get(Calendar.MINUTE);
    }

    private int numberMoney = 0; //用来记录单词的顺序

    //保存单词序号
    private void saveNumberMoney() {
        try {
            getNumberMoney();
        } catch (Exception e) {
            e.printStackTrace();
        }
        SharedPreferences.Editor editor = mContext.getSharedPreferences("numberMoney", Context.MODE_PRIVATE).edit();
        editor.putInt("numberMoney", numberMoney);
        editor.commit();
    }

    //获取单词序号
    private void getNumberMoney() {
        SharedPreferences sp = mContext.getSharedPreferences("numberMoney", Context.MODE_PRIVATE);
        numberMoney = sp.getInt("numberMoney", 0);
        numberMoney++; //获取到上次的数据后，需要将序号加1，供保存时使用
    }

    private int numberEvent = 0; //用来记录事件的顺序

    //保存事件序号
    private void saveNumberEvent() {
        SharedPreferences.Editor editor = mContext.getSharedPreferences("numberEvent", Context.MODE_PRIVATE).edit();
        editor.putInt("numberEvent", numberEvent);
        editor.commit();
    }

    //获取事件序号
    private void getNumberEvent() {
        SharedPreferences sp = mContext.getSharedPreferences("numberEvent", Context.MODE_PRIVATE);
        numberEvent = sp.getInt("numberEvent", 0);
        numberEvent++; //获取到上次的数据后，需要将序号加1，供保存时使用
    }

    //保存用户设定的数据到本地
    private void saveEventData() {
        try {
            getNumberEvent(); //获取用于本次保存所需的序号
        } catch (Exception e) {
            e.printStackTrace();
        }
        SharedPreferences.Editor editor = mContext.getSharedPreferences("event" + numberEvent, Context.MODE_PRIVATE).edit();
        editor.putInt("yearFinal", yearGet);
        editor.putInt("monthFinal", monthGet);
        editor.putInt("dayFinal", dayGet);
        editor.putInt("hourFinal", hourGet);
        editor.putInt("minuteFinal", minuteGet);
        editor.putString("eventFinal", inputEvent);
        editor.commit();
        saveNumberEvent();  //保存序号
    }

}
