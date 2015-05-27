package com.youxin.pc.firstapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class QuizActivity extends Activity {

    //��ע��������������Ա��ʵ�����������Ƶ� m ǰ׺����ǰ׺��Android�������ѭ������Լ��
    private Button mTrueButton;
    private Button mFalseButton;

    //Activity���౻������onCreate�����ᱻ����
    //activity����������Ҫ��ȡ�����������Լ����û�����
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //��ȡ�û�����
        //�����ǲ��ֵ���ԴID
        setContentView(R.layout.activity_quiz);

        //�������
        mTrueButton=(Button)findViewById(R.id.true_button);
        mFalseButton=(Button)findViewById(R.id.false_button);

        //���ü�����,ͨ�������ڲ��࣬��ʵ����OnclickListener�ӿ�
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //������ʾ��Ϣ
                //�÷����ĵ�һ������Context��ͨ���� Activity ��һ��ʵ���� Activity ������� Context �����ࣩ ��
                //�ڶ�������resId,��toast����ʾ�ַ�����Ϣ����ԴID��Toast ��������� context �����ҵ���ʹ���ַ�������ԴID��
                //����������duration,ͨ�������� Toast �����е�һ��������ָ��toast��Ϣ��ʾ�ĳ���ʱ�䡣
                //ע���һ����������ֱ��Ϊthis����Ϊthis��ָ���������ڲ��࣬������QuizActivity
                Toast.makeText(QuizActivity.this,R.string.incorrect_toast,Toast.LENGTH_SHORT).show();
            }
        });
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(QuizActivity.this, R.string.correct_toast,Toast.LENGTH_SHORT).show();
            }
        });

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
