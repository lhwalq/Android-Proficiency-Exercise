package com.proficiency.view.home;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.core.lib.helper.NavigationHelper;
import com.proficiency.R;
import com.proficiency.basics.ui.AppBaseCompatActivity;
import com.proficiency.bean.resp.SpecificBean;
import com.proficiency.common.widget.CommonChromeClient;
import com.proficiency.common.widget.CustomWebView;
import com.proficiency.view.home.presenter.DetailPresenter;
import com.proficiency.view.home.view.DetailView;

/**
 * Created by hp on 2017/8/4.
 */

public class DetailActivity extends AppBaseCompatActivity<DetailView, DetailPresenter> implements DetailView {

    private static final String SPECIFIC = "specific";

    private SpecificBean specificBean;

    private CustomWebView wvDetail;

    public static void startDetailActivity(Activity context, SpecificBean specificBean) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(SPECIFIC, specificBean);
        NavigationHelper.slideActivity(context, DetailActivity.class, bundle, false);
    }

    private void getBundle() {
        Bundle bundle = getIntent().getExtras();
        if (null != bundle && bundle.containsKey(SPECIFIC)) {
            specificBean = (SpecificBean) bundle.getSerializable(SPECIFIC);
        }
    }

    @Override
    protected void onCreateView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_detail);

        getBundle();
    }

    @Override
    protected void initView() {
        super.initView();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_menu_back);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doBack(-1, null);
            }
        });

        ActionBar actionBar = getSupportActionBar();
        if (null != actionBar) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
        }

        wvDetail = (CustomWebView) findViewById(R.id.wv_detail);
    }

    @Override
    protected void initData() {
        super.initData();

        if (null != specificBean) {
            ((TextView) findViewById(R.id.toolbar_title)).setText(specificBean.getDesc());
            ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressbar);
            wvDetail.setWebChromeClient(new CommonChromeClient(getBaseContext(), progressBar));
            wvDetail.loadUrl(specificBean.getUrl());
        }
    }

    @Override
    protected DetailPresenter setPresenter() {
        return new DetailPresenter();
    }
}
