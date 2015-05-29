package com.youxin.geoquiz10;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by pc on 2015/5/28.
 */
public class CheatActivity extends Activity {
    private TextView mAnswerTextView;
    private Button mshowAnswer;

    private boolean mAnswerIsTrue;
    private static final String EXTRA_ANSWER_IS_TRUE="com.youxin.geoquiz10.answer_is_true";//保存问题的答案key，用于传给CheatActivity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);
        mAnswerIsTrue=getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE,false);
        mAnswerTextView=(TextView)findViewById(R.id.answerTextView);

        //给showAnswerButton添加点击事件
        mshowAnswer=(Button)findViewById(R.id.showAnswerButton);
        mshowAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mAnswerIsTrue)
                    mAnswerTextView.setText(R.string.true_button);
                else
                    mAnswerTextView.setText(R.string.false_button);
            }
        });

    }
}
