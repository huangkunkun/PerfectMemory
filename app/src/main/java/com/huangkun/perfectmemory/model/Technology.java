package com.huangkun.perfectmemory.model;

import android.graphics.Bitmap;

/**
 * Created by hi on 2016/9/8.
 */
public class Technology {
    private String time; //返回数据中的时间
    private String title; //返回数据中的标题
    private Bitmap mBitmap; //返回数据中的图片
    private String url; //返回数据中详细内容的url地址

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Bitmap getBitmap() {
        return mBitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        mBitmap = bitmap;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
