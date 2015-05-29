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
    private int mCurrentQuestionIndex=0;//��ǰ��Ŀ���
    private static final String KEY_INDEX="index";//��Ϊ���浱ǰ��Ŀ��ŵļ�
    private static final String EXTRA_ANSWER_IS_TRUE="com.youxin.geoquiz10.answer_is_true";//��������Ĵ�key�����ڴ���CheatActivity
    private static final String TAG="QuizActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Android�ڲ��� android.util.log ���ܹ�������־��Ϣ��ϵͳ����Ĺ�����־���ġ� Log ���кü�����־��Ϣ��¼������
        //d �����š�debug������˼��������ʾ��־��Ϣ�ļ���
        // ��һ��������ʾ��־��Ϣ����Դ.ͨ��������Ϊֵ�� TAG �������롣�����������׿�����־��Ϣ����Դ��
        //�ڶ���������ʾ��־�ľ������ݡ�
        Log.d(TAG, "onCreate(Bundle) called");

        setContentView(R.layout.activity_quiz);


        mTrueButton=(Button)findViewById(R.id.true_button);
        mFalseButton=(Button)findViewById(R.id.false_button);
        mPrevButton=(ImageButton)findViewById(R.id.prev_button);
        mNextButton=(ImageButton)findViewById(R.id.next_button);
        mQuestionTextView=(TextView)findViewById(R.id.question_text_view);
        mCheatButton=(Button)findViewById(R.id.cheat_button);

        //Ϊtrue��ť��Ӽ�����
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(true);
            }
        });
        //Ϊfalse��ť��Ӽ�����
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(false);
            }
        });
        //Ϊprev��ť��Ӽ�����
        mPrevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentQuestionIndex--;
                if (mCurrentQuestionIndex==-1)
                    mCurrentQuestionIndex=mAnswerKey.length-1;
                updateQuestion();

            }
        });
        //Ϊnext��ť��Ӽ�����
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentQuestionIndex=(++mCurrentQuestionIndex)%mAnswerKey.length;
                updateQuestion();
            }
        });

        //Ϊtextview��Ӽ�����
        mQuestionTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentQuestionIndex = (++mCurrentQuestionIndex) % mAnswerKey.length;
                updateQuestion();
            }
        });
        //ΪcheatButton��Ӽ�����
        mCheatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(QuizActivity.this,CheatActivity.class);
                boolean isTrue=mAnswerKey[mCurrentQuestionIndex].isTrueQuestion();//��ȡ��ǰ���Ƿ���ȷ�����ݸ�extra
                i.putExtra(EXTRA_ANSWER_IS_TRUE,isTrue);
                startActivity(i);
            }
        });

        if(savedInstanceState!=null)
            mCurrentQuestionIndex=savedInstanceState.getInt(KEY_INDEX,0);
        updateQuestion();//��ʼʱ�����ȳ���һ������
    }

    //���������Ƿ���ȷ
    protected void checkAnswer(boolean userPressedTrue){
        boolean answerIsTrue=mAnswerKey[mCurrentQuestionIndex].isTrueQuestion();//����ı�׼��
        int messageResId = 0;
        if(userPressedTrue==answerIsTrue)
            messageResId = R.string.correct_toast;
        else
            messageResId=R.string.incorrect_toast;
        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show();
    }

    //��������
    protected void updateQuestion(){
        mQuestionTextView=(TextView)findViewById(R.id.question_text_view);
        mQuestionTextView.setText(mAnswerKey[mCurrentQuestionIndex].getQuestion());
    }

    //�������е��������ڷ���
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

    //�÷������ڱ������ݣ���onpause onstop ondestroy֮ǰ����
    //�������ת���������ø÷������ڵ��� onpause onstop ondestory oncreate onstart onresume
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
