package com.youxin.criminalintent;

import android.support.v4.app.Fragment;

/**
 * Created by pc on 2015/6/4.
 */
public class CrimeListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new CrimeListFragment();
    }
}
