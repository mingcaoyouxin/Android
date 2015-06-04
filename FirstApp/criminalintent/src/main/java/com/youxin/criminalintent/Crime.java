package com.youxin.criminalintent;


import android.text.format.DateFormat;

import java.util.Date;
import java.util.UUID;

/**
 * Created by pc on 2015/6/2.
 */
public class Crime {
    private UUID mId;//识别 Crime实例的唯一元素
    private String mTitle;//描述性名称
    private Date mDate;//当前日期，作为crime的默认的发生时间
    private boolean mSolved;

    public String getDate() {
        CharSequence date = new String("EEEE,MMM dd,yyyy");
        return DateFormat.format(date, mDate).toString();
    }

    public void setDate(Date date) {
        mDate = date;
    }

    public boolean isSolved() {
        return mSolved;
    }

    public void setSolved(boolean solved) {
        mSolved = solved;
    }


    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public UUID getId() {
        return mId;
    }

    public void setId(UUID id) {
        mId = id;
    }


    //生成唯一标识符
    public Crime() {
        this.mId = UUID.randomUUID();
        mDate = new Date();
    }
    @Override
    public String toString(){
        return mTitle;
    }
}
