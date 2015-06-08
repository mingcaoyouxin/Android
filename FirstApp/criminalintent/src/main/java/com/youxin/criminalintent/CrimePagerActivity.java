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
 * ���������� ViewPager
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
        //��ȡ���ݼ�
        mCrimes=CrimeLab.get(this).getCrimes();
        //��ȡactivity��FragmentManagerʵ��
        FragmentManager fm=getSupportFragmentManager();
        //����adapterΪFragmentStatePagerAdapter��һ������ʵ��
        //FragmentStatePagerAdapter�����ǵĴ���������ViewPager�Ի���Эͬ����
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fm) {
            //���������Ƚ� getItem(int)�������ص�fragment��Ӹ�activity��Ȼ�����ʹ��fragment����Լ��Ĺ�����
            // ���ǽ����ص�fragment��Ӹ��й�activity��������Viewpager �ҵ�fragment����ͼ��һһ��Ӧ
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
                //���ñ���
                Crime crime=mCrimes.get(position);
                if(crime.getTitle()!=null)
                    setTitle(crime.getTitle());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        //��ȡ���ݣ�����λ��
        UUID crimeId=(UUID)getIntent().getSerializableExtra(CrimeFragment.EXTRA_CRIME_ID);
        for (int i=0;i<mCrimes.size();i++){
            if(mCrimes.get(i).getId().equals(crimeId)){
                mViewPager.setCurrentItem(i);
                break;
            }
        }


    }

}
