package com.youxin.geoquiz10;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


public class QuizActivity extends ActionBarActivity {

    private Button mTrueButton;
    private Button mFalseButton;
 /*   private Button mPrevButton;
    private Button mNextButton;*/
    private Button mCheatButton;
    private ImageButton mPrevButton;
    private ImageButton mNextButton;

    private TextView mQuestionTextView;
    private TrueFalse[] mAnswerKey=new TrueFalse[]{
            new TrueFalse(R.string.question_africa,false),
            new TrueFalse(R.string.question_americas,true),
            new TrueFalse(R.string.question_asia,true),
            new TrueFalse(R.string.question_mideast,false),
            new TrueFalse(R.string.question_oceans,true)
    };
    private int mCurrentQuestionIndex=0;//当前题目编号
    private static final String KEY_INDEX="index";//作为保存当前题目编号的键
    private static final String EXTRA_ANSWER_IS_TRUE="com.youxin.geoquiz10.answer_is_true";//保存问题的答案key，用于传给CheatActivity
    private static final String TAG="QuizActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Android内部的 android.util.log 类能够发送日志信息到系统级别的共享日志中心。 Log 类有好几个日志信息记录方法。
        //d 代表着“debug”的意思，用来表示日志信息的级别。
        // 第一个参数表示日志信息的来源.通常以类名为值的 TAG 常量传入。这样，很容易看出日志信息的来源。
        //第二个参数表示日志的具体内容。
        Log.d(TAG, "onCreate(Bundle) called");

        setContentView(R.layout.activity_quiz);


        mTrueButton=(Button)findViewById(R.id.true_button);
        mFalseButton=(Button)findViewById(R.id.false_button);
        mPrevButton=(ImageButton)findViewById(R.id.prev_button);
        mNextButton=(ImageButton)findViewById(R.id.next_button);
        mQuestionTextView=(TextView)findViewById(R.id.question_text_view);
        mCheatButton=(Button)findViewById(R.id.cheat_button);

        //为true按钮添加监听器
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(true);
            }
        });
        //为false按钮添加监听器
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(false);
            }
        });
        //为prev按钮添加监听器
        mPrevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentQuestionIndex--;
                if (mCurrentQuestionIndex==-1)
                    mCurrentQuestionIndex=mAnswerKey.length-1;
                updateQuestion();

            }
        });
        //为next按钮添加监听器
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentQuestionIndex=(++mCurrentQuestionIndex)%mAnswerKey.length;
                updateQuestion();
            }
        });

        //为textview添加监听器
        mQuestionTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentQuestionIndex = (++mCurrentQuestionIndex) % mAnswerKey.length;
                updateQuestion();
            }
        });
        //为cheatButton添加监听器
        mCheatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(QuizActivity.this,CheatActivity.class);
                boolean isTrue=mAnswerKey[mCurrentQuestionIndex].isTrueQuestion();//获取当前答案是否正确，传递给extra
                i.putExtra(EXTRA_ANSWER_IS_TRUE,isTrue);
                startActivity(i);
            }
        });

        if(savedInstanceState!=null)
            mCurrentQuestionIndex=savedInstanceState.getInt(KEY_INDEX,0);
        updateQuestion();//初始时候首先出来一个问题
    }

    //检查问题答案是否正确
    protected void checkAnswer(boolean userPressedTrue){
        boolean answerIsTrue=mAnswerKey[mCurrentQuestionIndex].isTrueQuestion();//问题的标准答案
        int messageResId = 0;
        if(userPressedTrue==answerIsTrue)
            messageResId = R.string.correct_toast;
        else
            messageResId=R.string.incorrect_toast;
        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show();
    }

    //更新问题
    protected void updateQuestion(){
        mQuestionTextView=(TextView)findViewById(R.id.question_text_view);
        mQuestionTextView.setText(mAnswerKey[mCurrentQuestionIndex].getQuestion());
    }

    //覆盖所有的生命周期方法
    @Override
    public void onStart(){
        super.onStart();
        Log.d(TAG, "onStart() called");
    }
    @Override
    public void onResume(){
        super.onResume();
        Log.d(TAG,"onResume() called");
    }

    @Override
    public void onPause(){
        super.onPause();
        Log.d(TAG,"onPause() called");
    }
    @Override
    public void onStop(){
        super.onStop();
        Log.d(TAG,"onStop() called");
    }
    @Override
    public void onDestroy(){
        super.onDestroy();
        Log.d(TAG,"onDestroy() called");
    }

    //该方法用于保存数据，在onpause onstop ondestroy之前调用
    //如果发生转屏，则会调用该方法后在调用 onpause onstop ondestory oncreate onstart onresume
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        Log.i(TAG,"onSaveInstanceState");
        savedInstanceState.putInt(KEY_INDEX,mCurrentQuestionIndex);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_quiz, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
