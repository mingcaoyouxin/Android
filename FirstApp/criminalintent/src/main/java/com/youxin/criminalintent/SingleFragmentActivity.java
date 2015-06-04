package com.youxin.criminalintent;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

/**
 * Created by pc on 2015/6/4.
 */
public abstract class SingleFragmentActivity extends FragmentActivity {
    protected abstract Fragment createFragment();

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
        //��Ϊʹ����֧�ֿ⼰FragmentActivity�࣬���������õķ�����getSupportFragmentManager
        //��������Ǽ����ԣ���ֱ�Ӽ̳�Activity�ಢ����getFragmentManager����
        FragmentManager fm = getSupportFragmentManager();

        //ʹ��R.id.fragmentContainer��������ͼ��ԴID���� FragmentManager�����ȡfragment����Ҫ��ȡ��fragment�ڶ������Ѿ����ڣ� FragmentManager �漴�Ὣ֮����
        Fragment fragment = fm.findFragmentById(R.id.fragment_Container);

        if (fragment == null) {
            //�������ύ��һ��fragment����
            //fragment����������ӡ��Ƴ������ӡ�������滻fragment�����е�fragment������ʹ��fragment������ʱ��װ��������װ�û�����ĺ��ķ�ʽ��
            //FragmentManager������Fragment����Ļ���ջ
            fragment = createFragment();
            //beginTransaction����������FragmentTransactionʵ����
            fm.beginTransaction().add(R.id.fragment_Container, fragment).commit();
        }
    }
}
