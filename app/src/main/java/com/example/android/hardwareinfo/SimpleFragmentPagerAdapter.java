package com.example.android.hardwareinfo;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by sankar on 4/20/17.
 */

public class SimpleFragmentPagerAdapter extends FragmentPagerAdapter {

    public SimpleFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if(position == 0) {
            return "SOC";
        } else {
            return "DEVICE";
        }
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new Soc();
        } else {
            return new Device();
        }
    }

    @Override
    public int getCount() {
        return 4;
    }
}
