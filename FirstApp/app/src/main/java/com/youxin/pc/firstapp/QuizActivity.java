package com.youxin.pc.firstapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class QuizActivity extends Activity {

    //请注意新增的两个成员（实例）变量名称的 m 前缀。该前缀是Android编程所遵循的命名约定
    private Button mTrueButton;
    private Button mFalseButton;

    //Activity子类被创建后，onCreate方法会被调用
    //activity创建后，它需要获取并管理属于自己的用户界面
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //获取用户界面
        //参数是布局的资源ID
        setContentView(R.layout.activity_quiz);

        //引用组件
        mTrueButton=(Button)findViewById(R.id.true_button);
        mFalseButton=(Button)findViewById(R.id.false_button);

        //设置监听器,通过匿名内部类，其实现了OnclickListener接口
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //创建提示消息
                //该方法的第一个参数Context，通常是 Activity 的一个实例（ Activity 本身就是 Context 的子类） 。
                //第二个参数resId,是toast待显示字符串消息的资源ID。Toast 类必须利用 context 才能找到并使用字符串的资源ID。
                //第三个参数duration,通常是两个 Toast 常量中的一个，用来指定toast消息显示的持续时间。
                //注意第一个参数不能直接为this，因为this是指代的匿名内部类，而不是QuizActivity
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
