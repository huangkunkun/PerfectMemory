package com.huangkun.perfectmemory.model;

/**
 * Created by hi on 2016/9/5.
 */
public class Note {
    private String content;
    private String time;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
