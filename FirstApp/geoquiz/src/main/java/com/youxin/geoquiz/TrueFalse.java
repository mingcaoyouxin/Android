package com.youxin.geoquiz;

/**
 * Created by pc on 2015/5/27.
 */
public class TrueFalse {
    private int mQuestion;//�����������֪ʶ�����ַ�������ԴID��������ֱ�Ӵ洢�ַ�������
    private boolean mTrueQuestion;//����ȷ�����Ƿ���ȷ

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