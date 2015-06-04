package com.youxin.criminalintent;

import android.content.Context;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by pc on 2015/6/4.
 * ����Crimes ���ݼ��д洢�أ� �����洢 Crime ����
 */
public class CrimeLab {
    private Context mAppContext;
    private static CrimeLab sCrimeLab;
    private ArrayList<Crime> mCrimes;

    public ArrayList<Crime> getCrimes() {
        return mCrimes;
    }

    //ʹ��Context���������������������activity����ȡ��Ŀ��Դ������Ӧ�õ�˽�пռ������
    private CrimeLab(Context appContext) {
        mAppContext = appContext;
        mCrimes = new ArrayList<Crime>();
        //�������100��Crime
        for (int i = 0; i < 100; i++) {
            Crime c = new Crime();
            c.setTitle("Crime #" + i);
            c.setSolved(i % 2 == 0); // every other one
            mCrimes.add(c);
        }
    }

    public static CrimeLab get(Context c) {
        if (sCrimeLab == null) {
            //�� get(Context) ��������ǲ�û��ֱ�ӽ�Context�����������췽������Context������һ��Activity��Ҳ��������һ�� Context ������ Service ��
            // ��Ӧ�õ�������������� �����޷���ֻ֤Ҫ CrimeLab ��Ҫ�õ� Context ��Context ��һ������ڡ�
            // ��ˣ�Ϊ��֤���������� Context ����ʹ�ã��ɵ��� getApplicationContext() ����������ȷ���Ƿ���ڵ� Context �滻��application context��
            // application context�����Ӧ�õ�ȫ���� Context ���κ�ʱ��ֻҪ��Ӧ�ò���ĵ�������Ӧ��һֱʹ��application context��
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
