package com.youxin.criminalintent;


import android.support.v4.app.Fragment;

import java.util.UUID;


public class CrimeActivity extends SingleFragmentActivity {


    @Override
    protected Fragment createFragment() {
        //return new CrimeFragment();
        UUID crimeId=(UUID)getIntent().getSerializableExtra(CrimeFragment.EXTRA_CRIME_ID);
        return CrimeFragment.newInstance(crimeId);
    }
}
