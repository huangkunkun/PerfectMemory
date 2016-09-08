package com.huangkun.perfectmemory.model;


/**
 * Created by hi on 2016/8/10.
 */
public class Money {
   // private UUID id;
    private double moneyAmount;
    private String moneyMean;
    private String moneyTime;

//    public Money() {
//        id = UUID.randomUUID();
//    }
//
//    public UUID getId() {
//        return id;
//    }

    public double getMoneyAmount() {
        return moneyAmount;
    }

    public void setMoneyAmount(double moneyAmount) {
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
