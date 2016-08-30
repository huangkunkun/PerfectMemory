package com.huangkun.perfectmemory.model;

import android.content.Context;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by hi on 2016/8/30.
 */
public class MoneyLab {

    private ArrayList<Money> mMoneys;

    private static MoneyLab sMoneyLab;
    private Context mContext;

    private MoneyLab(Context appContext){
        mContext = appContext;
        mMoneys = new ArrayList<>();
    }

    public static MoneyLab getMoneyLab(Context context){
        if (sMoneyLab == null){
            sMoneyLab = new MoneyLab(context.getApplicationContext());
        }
        return sMoneyLab;
    }

    public ArrayList<Money> getMoneys(){
        return mMoneys;
    }

    public Money getMoney(UUID uuid){
        for (Money m : mMoneys){
            if (m.getId().equals(uuid)){
                return m;
            }
        }
        return null;
    }

}
