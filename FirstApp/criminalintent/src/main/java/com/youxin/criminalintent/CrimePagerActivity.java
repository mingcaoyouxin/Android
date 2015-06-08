package com.youxin.criminalintent;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by pc on 2015/6/5.
 * 创建并管理 ViewPager
 */
public class CrimePagerActivity extends FragmentActivity {
    private ViewPager mViewPager;
    private ArrayList<Crime> mCrimes;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        mViewPager=new ViewPager(this);
        mViewPager.setId(R.id.viewPager);
        setContentView(mViewPager);
        //获取数据集
        mCrimes=CrimeLab.get(this).getCrimes();
        //获取activity的FragmentManager实例
        FragmentManager fm=getSupportFragmentManager();
        //设置adapter为FragmentStatePagerAdapter的一个匿名实例
        //FragmentStatePagerAdapter是我们的代理，负责与ViewPager对话并协同工作
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fm) {
            //代理需首先将 getItem(int)方法返回的fragment添加给activity，然后才能使用fragment完成自己的工作。
            // 就是将返回的fragment添加给托管activity，并帮助Viewpager 找到fragment的视图并一一对应
            @Override
            public Fragment getItem(int position) {
                Crime crime=mCrimes.get(position);
                return CrimeFragment.newInstance(crime.getId());
            }

            @Override
            public int getCount() {
                return mCrimes.size();
            }
        });
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                //设置标题
                Crime crime=mCrimes.get(position);
                if(crime.getTitle()!=null)
                    setTitle(crime.getTitle());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        //获取数据，设置位置
        UUID crimeId=(UUID)getIntent().getSerializableExtra(CrimeFragment.EXTRA_CRIME_ID);
        for (int i=0;i<mCrimes.size();i++){
            if(mCrimes.get(i).getId().equals(crimeId)){
                mViewPager.setCurrentItem(i);
                break;
            }
        }


    }

}
