package com.youxin.criminalintent;

import android.content.Context;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by pc on 2015/6/4.
 * 管理Crimes 数据集中存储池， 用来存储 Crime 对象
 */
public class CrimeLab {
    private Context mAppContext;
    private static CrimeLab sCrimeLab;
    private ArrayList<Crime> mCrimes;

    public ArrayList<Crime> getCrimes() {
        return mCrimes;
    }

    //使用Context参数，单例可以完成启动activity，获取项目资源，查找应用的私有空间等任务
    private CrimeLab(Context appContext) {
        mAppContext = appContext;
        mCrimes = new ArrayList<Crime>();
        //首先添加100个Crime
        for (int i = 0; i < 100; i++) {
            Crime c = new Crime();
            c.setTitle("Crime #" + i);
            c.setSolved(i % 2 == 0); // every other one
            mCrimes.add(c);
        }
    }

    public static CrimeLab get(Context c) {
        if (sCrimeLab == null) {
            //在 get(Context) 方法里，我们并没有直接将Context参数传给构造方法。该Context可能是一个Activity，也可能是另一个 Context 对象，如 Service 。
            // 在应用的整个生命周期里， 我们无法保证只要 CrimeLab 需要用到 Context ，Context 就一定会存在。
            // 因此，为保证单例总是有 Context 可以使用，可调用 getApplicationContext() 方法，将不确定是否存在的 Context 替换成application context。
            // application context是针对应用的全局性 Context 。任何时候，只要是应用层面的单例，就应该一直使用application context。
            sCrimeLab = new CrimeLab(c.getApplicationContext());
        }
        return sCrimeLab;
    }

    public Crime getCrime(UUID id) {
        for (Crime c : mCrimes)
            if (c.getId().equals(id))
                return c;
        return null;
    }

}
