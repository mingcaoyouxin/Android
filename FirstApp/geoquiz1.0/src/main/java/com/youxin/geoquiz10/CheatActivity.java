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
    private boolean isAnswerShown;//保存是否欺骗的值
    private static final String Cheater="cheat";//作为保存是否欺骗的键
    private boolean mAnswerIsTrue;
    private static final String EXTRA_ANSWER_IS_TRUE="com.youxin.geoquiz10.answer_is_true";//保存问题的答案key
    public static final String EXTRA_ANSWER_SHOWN="com.youxin.geoquiz10.answer_shown";//用来保存是否点击showAnswer按钮

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);//设置该activity的布局
        mAnswerTextView=(TextView)findViewById(R.id.answerTextView);

        //初始设置showAnswer按钮未点击，并将结果返回
        //这个是否点击有可能是转屏以前保存的数据
        if(savedInstanceState!=null)
            isAnswerShown=savedInstanceState.getBoolean(Cheater, false);//获取保存的数据，并且默认为false
        setAnswerShowResult(isAnswerShown);//将结果返回给QuizActivity
        mAnswerIsTrue=getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE,false);//获取intent的extra信息
        //如果发生转屏，我们需要对mAnswerTextView的值进行更新，否则mAnswerTextView的值会丢失
        if(savedInstanceState!=null)//说明转屏了
            if(isAnswerShown) {//说明点击过showAnswer了
                if(mAnswerIsTrue)
                    mAnswerTextView.setText(R.string.true_button);
                else
                    mAnswerTextView.setText(R.string.false_button);
            }


        //给showAnswerButton添加点击事件
        mshowAnswer=(Button)findViewById(R.id.showAnswerButton);
        mshowAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mAnswerIsTrue)
                    mAnswerTextView.setText(R.string.true_button);
                else
                    mAnswerTextView.setText(R.string.false_button);
                setAnswerShowResult(true);//设置showAnswer按钮已点击，并将结果返回
                isAnswerShown=true;
            }
        });

    }

    //保存是否点击showAnswer按钮，并把结果返回
    private void setAnswerShowResult(boolean isAnswerShown){
        Intent data=new Intent();
        data.putExtra(EXTRA_ANSWER_SHOWN,isAnswerShown);
        setResult(RESULT_OK, data);
    }


    //该方法用于保存数据，在onpause onstop ondestroy之前调用
    //如果发生转屏，则会调用该方法再调用 onpause onstop ondestory oncreate onstart onresume
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        //Log.i(TAG, "onSaveInstanceState");
        savedInstanceState.putBoolean(Cheater, isAnswerShown);//转屏时保存是否answerShown
    }
}
