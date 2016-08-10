package com.huangkun.perfectmemory.model;

/**
 * Created by hi on 2016/8/10.
 */
public class Word {
    private int id;
    private String wordName;
    private String wordMean;
    private String wordTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWordName() {
        return wordName;
    }

    public void setWordName(String wordName) {
        this.wordName = wordName;
    }

    public String getWordMean() {
        return wordMean;
    }

    public void setWordMean(String wordMean) {
        this.wordMean = wordMean;
    }

    public String getWordTime() {
        return wordTime;
    }

    public void setWordTime(String wordTime) {
        this.wordTime = wordTime;
    }
}
