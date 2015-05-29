package com.youxin.geoquiz;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


public class QuizActivity extends Activity {
    private Button mTrueButton;
    private Button mFalseButton;
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
    private int mCurrentQuestionIndex=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        mTrueButton=(Button)findViewById(R.id.true_button);
        mFalseButton=(Button)findViewById(R.id.false_button);
        mPrevButton=(ImageButton)findViewById(R.id.prev_button);
        mNextButton=(ImageButton)findViewById(R.id.next_button);

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
                mCurrentQuestionIndex=(++mCurrentQuestionIndex)%mAnswerKey.length;
                updateQuestion();
            }
        });

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
