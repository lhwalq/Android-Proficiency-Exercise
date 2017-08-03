package com.proficiency.view.home;

import android.os.Bundle;

import com.core.lib.helper.Helper;
import com.proficiency.R;
import com.proficiency.basics.ui.RefreshAndMoreFragment;
import com.proficiency.view.home.adapter.SpecificAdapter;
import com.proficiency.view.home.presenter.SpecificPresenter;
import com.proficiency.view.home.view.SpecificView;

/**
 * @author linhuan on 2017/8/3 下午10:55
 */
public class SpecificFragment extends RefreshAndMoreFragment<SpecificView, SpecificPresenter, SpecificAdapter> implements SpecificView {

    private static final String TITLE = "title";

    private String titleStr = "";

    public static SpecificFragment newInstance(String title) {
        Bundle bundle = new Bundle();
        bundle.putString(TITLE, title);
        SpecificFragment pageFragment = new SpecificFragment();
        pageFragment.setArguments(bundle);
        return pageFragment;
    }

    @Override
    protected void onCreateViewLazy(Bundle savedInstanceState) {
        super.onCreateViewLazy(savedInstanceState);
        setContentView(R.layout.fragment_specific);

        getBundle();
    }

    private void getBundle() {
        Bundle bundle = getArguments();
        if (Helper.isNotNull(bundle) && bundle.containsKey(TITLE)) {
            titleStr = bundle.getString(TITLE);
        }
    }

    @Override
    protected SpecificAdapter setBaseAdapter() {
        return new SpecificAdapter();
    }

    @Override
    protected SpecificPresenter setPresenter() {
        return new SpecificPresenter();
    }

    @Override
    protected int setSwipeRefreshLayoutId() {
        return R.id.mrl_content;
    }

    @Override
    protected int setRecyclerViewId() {
        return R.id.rcv_content;
    }

    @Override
    protected void loadData(int page, int pageNum) {
        getBasePresenter().getListData(titleStr, page, pageNum);
    }
}
