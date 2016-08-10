package floatball;

import android.support.v7.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.huangkun.perfectmemory.R;
import com.huangkun.perfectmemory.utils.MyApplication;

import floatball.interfaces.IMenu;



public class FloatBallMenu implements IMenu {
    private TextView tvLeftCenter, tvRightCenter, tvLeftGift, tvRightGift;
    private View leftLine, rightLine;
    private int menuWidth, menuHeight;
    private FloatBall mFloatBall;

    private Context mContext ;
    private MyClickListener listener = new MyClickListener();

    public FloatBallMenu(Context context){
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

    private class MyClickListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            if (v == tvLeftCenter || v == tvRightCenter){
                setWordDialog();
            }else if (v == tvLeftGift || v == tvRightGift){
                setEventDialog();
            }

        }
    }
    private void setWordDialog(){
        View view = LayoutInflater.from(MyApplication.getContext()).inflate(R.layout.dialog_word, null);
        EditText editMean = (EditText) view.findViewById(R.id.et_mean);
        EditText editWord = (EditText)view.findViewById(R.id.et_word);
        AlertDialog.Builder dialog = new AlertDialog.Builder(mContext);
        dialog.setView(view);
        dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }
        );
        dialog.setCancelable(true);
        dialog.show();
        mFloatBall.hideMenu();
    }
    private void setEventDialog(){
        View view = LayoutInflater.from(MyApplication.getContext()).inflate(R.layout.dialog_event, null);
        EditText editEvent = (EditText) view.findViewById(R.id.et_event);
        AlertDialog.Builder dialog = new AlertDialog.Builder(mContext);
        dialog.setView(view);
        dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }
        );
        dialog.setCancelable(true);
        dialog.show();
        mFloatBall.hideMenu();
    }
}
