package com.youxin.geoquiz;

/**
 * Created by pc on 2015/5/27.
 */
public class TrueFalse {
    private int mQuestion;//用来保存地理知识问题字符串的资源ID，而不是直接存储字符串问题
    private boolean mTrueQuestion;//用来确定答案是否正确

    public boolean isTrueQuestion() {
        return mTrueQuestion;
    }

    public void setTrueQuestion(boolean trueQuestion) {
        mTrueQuestion = trueQuestion;
    }

    public int getQuestion() {
        return mQuestion;
    }

    public void setQuestion(int question) {
        mQuestion = question;
    }


    public TrueFalse(int mQuestion, boolean mTrueQuestion) {
        this.mQuestion = mQuestion;
        this.mTrueQuestion = mTrueQuestion;
    }
}