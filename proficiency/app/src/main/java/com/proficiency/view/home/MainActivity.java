package com.proficiency.view.home;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import com.proficiency.basics.ui.AppBaseCompatActivity;
import com.proficiency.R;
import com.proficiency.view.home.adapter.MainAdapter;
import com.proficiency.view.home.presenter.MainPresenter;
import com.proficiency.view.home.view.MainView;

public class MainActivity extends AppBaseCompatActivity<MainView, MainPresenter> implements MainView {

    private ViewPager viewPager;
    private TabLayout tabLayout;

    private MainAdapter mainAdapter;

    @Override
    protected void onCreateView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void initView() {
        super.initView();

        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));

        mainAdapter = new MainAdapter(getSupportFragmentManager());
        viewPager = (ViewPager) findViewById(R.id.vp_content);
        viewPager.setAdapter(mainAdapter);
        tabLayout = (TabLayout) findViewById(R.id.tl_top);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
    }

    @Override
    protected boolean isSwipeback() {
        return false;
    }

    @Override
    protected MainPresenter setPresenter() {
        return new MainPresenter();
    }

    @Override
    protected void toFinish() {

    }
}
