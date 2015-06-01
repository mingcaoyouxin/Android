package com.youxin.geoquiz10;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by pc on 2015/5/28.
 */
public class CheatActivity extends Activity {
    private TextView mAnswerTextView;
    private Button mshowAnswer;
    private boolean isAnswerShown;//�����Ƿ���ƭ��ֵ
    private static final String Cheater="cheat";//��Ϊ�����Ƿ���ƭ�ļ�
    private boolean mAnswerIsTrue;
    private static final String EXTRA_ANSWER_IS_TRUE="com.youxin.geoquiz10.answer_is_true";//��������Ĵ�key
    public static final String EXTRA_ANSWER_SHOWN="com.youxin.geoquiz10.answer_shown";//���������Ƿ���showAnswer��ť

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);//���ø�activity�Ĳ���
        mAnswerTextView=(TextView)findViewById(R.id.answerTextView);

        //��ʼ����showAnswer��ťδ����������������
        //����Ƿ����п�����ת����ǰ���������
        if(savedInstanceState!=null)
            isAnswerShown=savedInstanceState.getBoolean(Cheater, false);//��ȡ��������ݣ�����Ĭ��Ϊfalse
        setAnswerShowResult(isAnswerShown);//��������ظ�QuizActivity
        mAnswerIsTrue=getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE,false);//��ȡintent��extra��Ϣ
        //�������ת����������Ҫ��mAnswerTextView��ֵ���и��£�����mAnswerTextView��ֵ�ᶪʧ
        if(savedInstanceState!=null)//˵��ת����
            if(isAnswerShown) {//˵�������showAnswer��
                if(mAnswerIsTrue)
                    mAnswerTextView.setText(R.string.true_button);
                else
                    mAnswerTextView.setText(R.string.false_button);
            }


        //��showAnswerButton��ӵ���¼�
        mshowAnswer=(Button)findViewById(R.id.showAnswerButton);
        mshowAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mAnswerIsTrue)
                    mAnswerTextView.setText(R.string.true_button);
                else
                    mAnswerTextView.setText(R.string.false_button);
                setAnswerShowResult(true);//����showAnswer��ť�ѵ���������������
                isAnswerShown=true;
            }
        });

    }

    //�����Ƿ���showAnswer��ť�����ѽ������
    private void setAnswerShowResult(boolean isAnswerShown){
        Intent data=new Intent();
        data.putExtra(EXTRA_ANSWER_SHOWN,isAnswerShown);
        setResult(RESULT_OK, data);
    }


    //�÷������ڱ������ݣ���onpause onstop ondestroy֮ǰ����
    //�������ת���������ø÷����ٵ��� onpause onstop ondestory oncreate onstart onresume
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        //Log.i(TAG, "onSaveInstanceState");
        savedInstanceState.putBoolean(Cheater, isAnswerShown);//ת��ʱ�����Ƿ�answerShown
    }
}
