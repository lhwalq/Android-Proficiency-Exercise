package com.proficiency.view.home.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.core.lib.helper.ResourceHelper;
import com.proficiency.R;
import com.proficiency.view.home.SpecificFragment;

/**
 * @author linhuan on 2017/8/3 下午10:48
 */
public class MainAdapter extends FragmentPagerAdapter {

    private String[] titleStr = ResourceHelper.getStringArray(R.array.main_data);

    public MainAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return SpecificFragment.newInstance(titleStr[position]);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titleStr[position];
    }

    @Override
    public int getCount() {
        return titleStr.length;
    }

}
