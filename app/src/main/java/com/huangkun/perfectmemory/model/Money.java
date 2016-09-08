package com.huangkun.perfectmemory.model;


/**
 * Created by hi on 2016/8/10.
 */
public class Money {
   // private UUID id;
    private int moneyAmount;
    private String moneyMean;
    private String moneyTime;

//    public Money() {
//        id = UUID.randomUUID();
//    }
//
//    public UUID getId() {
//        return id;
//    }

    public int getMoneyAmount() {
        return moneyAmount;
    }

    public void setMoneyAmount(int moneyAmount) {
        this.moneyAmount = moneyAmount;
    }

    public String getMoneyMean() {
        return moneyMean;
    }

    public void setMoneyMean(String moneyMean) {
        this.moneyMean = moneyMean;
    }

    public String getMoneyTime() {
        return moneyTime;
    }

    public void setMoneyTime(String moneyTime) {
        this.moneyTime = moneyTime;
    }
}
