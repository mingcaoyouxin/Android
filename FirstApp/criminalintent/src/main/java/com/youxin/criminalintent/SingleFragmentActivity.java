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
        //因为使用了支持库及FragmentActivity类，因此这里调用的方法是getSupportFragmentManager
        //如果不考虑兼容性，可直接继承Activity类并调用getFragmentManager方法
        FragmentManager fm = getSupportFragmentManager();

        //使用R.id.fragmentContainer的容器视图资源ID，向 FragmentManager请求获取fragment。如要获取的fragment在队列中已经存在， FragmentManager 随即会将之返还
        Fragment fragment = fm.findFragmentById(R.id.fragment_Container);

        if (fragment == null) {
            //创建并提交了一个fragment事务
            //fragment事务被用来添加、移除、附加、分离或替换fragment队列中的fragment。这是使用fragment在运行时组装和重新组装用户界面的核心方式。
            //FragmentManager管理者Fragment事务的回退栈
            fragment = createFragment();
            //beginTransaction创建并返回FragmentTransaction实例。
            fm.beginTransaction().add(R.id.fragment_Container, fragment).commit();
        }
    }
}
