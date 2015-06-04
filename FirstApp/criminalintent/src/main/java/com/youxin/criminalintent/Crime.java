package com.youxin.criminalintent;


import android.text.format.DateFormat;

import java.util.Date;
import java.util.UUID;

/**
 * Created by pc on 2015/6/2.
 */
public class Crime {
    private UUID mId;//ʶ�� Crimeʵ����ΨһԪ��
    private String mTitle;//����������
    private Date mDate;//��ǰ���ڣ���Ϊcrime��Ĭ�ϵķ���ʱ��
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


    //����Ψһ��ʶ��
    public Crime() {
        this.mId = UUID.randomUUID();
        mDate = new Date();
    }
    @Override
    public String toString(){
        return mTitle;
    }
}
