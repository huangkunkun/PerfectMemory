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
import com.huangkun.perfectmemory.db.WordDB;
import com.huangkun.perfectmemory.model.Word;
import com.huangkun.perfectmemory.utils.FormatTime;
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

    private long time; //记录当前时间的

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
        final Context context = parent.getContext();
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
        tvLeftCenter.setText("单词");
        tvLeftCenter.setOnClickListener(listener);
        tvLeftCenter.setTextSize(14);
        tvLeftCenter.setGravity(Gravity.CENTER);
        tvLeftCenter.setTextColor(Color.parseColor("#333333"));
        parent.addView(tvLeftCenter, childLayoutParams);
    }


    private void addRightMenu(RelativeLayout parent) {
        final Context context = parent.getContext();
        RelativeLayout.LayoutParams childLayoutParams = new RelativeLayout.LayoutParams(DensityUtil.dip2px(parent.getContext(), 52), DensityUtil.dip2px(parent.getContext(), 30));
        childLayoutParams.setMargins(DensityUtil.dip2px(parent.getContext(), 20), 0, 0, 0);
        tvRightCenter = new TextView(parent.getContext());
        tvRightCenter.setId(getId());
        tvRightCenter.setText("单词");
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
                setWordDialog();
            } else if (v == tvLeftGift || v == tvRightGift) {
                setEventDialog();
            }
        }
    }

    private String inputWord = "";
    private String inputMean = "";
    //弹出记录单词的对话框
    private void setWordDialog() {
        getCurrentTime();
        final String timeStr = FormatTime.formatTime(yearGet, monthGet, dayGet, hourGet, minuteGet);
        View view = LayoutInflater.from(MyApplication.getContext()).inflate(R.layout.dialog_word, null);
        EditText editMean = (EditText) view.findViewById(R.id.et_mean);
        EditText editWord = (EditText) view.findViewById(R.id.et_word);
        inputWord = editWord.getText().toString();
        inputMean = editMean.getText().toString();
        AlertDialog.Builder dialog = new AlertDialog.Builder(mContext);
        dialog.setView(view);
        dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (TextUtils.isEmpty(inputWord) || TextUtils.isEmpty(inputMean)) {
                            Toast.makeText(mContext, "单词或解释未输入", Toast.LENGTH_SHORT).show();
                        }
                        saveWordToSQL(inputWord, inputMean, timeStr); //将内容保存到数据库
                        saveNumberWord(); //保存单词的序号到本地
                        Toast.makeText(mContext, "保存成功", Toast.LENGTH_SHORT).show();
                    }


                }
        );
        dialog.setCancelable(true);
        dialog.show();
        mFloatBall.hideMenu();
    }

    private void saveWordToSQL(String inputWord, String inputMean, String timeStr) {
        Word word = new Word();
        word.setWordName(inputWord);
        word.setWordMean(inputMean);
        word.setWordTime(timeStr);
        WordDB wordDB = WordDB.getInstance(mContext);
        wordDB.saveWord(word);
    }

    private String inputEvent = "";
    private int yearGet = 0;
    private int monthGet = 0;
    private int dayGet = 0;
    private int hourGet = 0;
    private int minuteGet = 0;

    //弹出记录事件的对话框
    private void setEventDialog() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_event, null);
        EditText editEvent = (EditText) view.findViewById(R.id.et_event);
        inputEvent = editEvent.getText().toString();
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
        AlertDialog.Builder dialog = new AlertDialog.Builder(mContext);
        dialog.setView(view);
        dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (TextUtils.isEmpty(inputEvent)) {
                            Toast.makeText(mContext, "事件内容未输入", Toast.LENGTH_SHORT).show();
                        }
                        saveEventData();
                        Toast.makeText(mContext, "保存成功", Toast.LENGTH_SHORT).show();
                    }
                }
        );
        dialog.setCancelable(true);
        dialog.show();
        mFloatBall.hideMenu();
    }

    //获取当前时间
    private void getCurrentTime() {
        Calendar calendar = Calendar.getInstance();
        yearGet = calendar.get(Calendar.YEAR);
        monthGet = calendar.get(Calendar.MONTH);
        dayGet = calendar.get(Calendar.DAY_OF_MONTH);
        hourGet = calendar.get(Calendar.HOUR_OF_DAY);
        minuteGet = calendar.get(Calendar.MINUTE);
        time = calendar.getTimeInMillis();
    }

    private int numberWord = 0; //用来记录单词的顺序

    //保存单词序号
    private void saveNumberWord() {
        try{
            getNumberWord();
        }catch (Exception e){
            e.printStackTrace();
        }
        SharedPreferences.Editor editor = mContext.getSharedPreferences("numberWord", Context.MODE_PRIVATE).edit();
        editor.putInt("numberWord", numberWord);
        editor.commit();
    }

    //获取单词序号
    private void getNumberWord() {
        SharedPreferences sp = mContext.getSharedPreferences("numberWord", Context.MODE_PRIVATE);
        numberEvent = sp.getInt("numberWord", 0);
        numberWord++; //获取到上次的数据后，需要将序号加1，供保存时使用
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
