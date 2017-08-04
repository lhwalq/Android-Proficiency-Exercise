package com.proficiency.view.home;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.core.lib.helper.Helper;
import com.core.lib.helper.ResourceHelper;
import com.proficiency.R;
import com.proficiency.basics.ui.RefreshAndMoreFragment;
import com.proficiency.common.widget.RecycleViewDivider;
import com.proficiency.view.home.adapter.SpecificAdapter;
import com.proficiency.view.home.presenter.SpecificPresenter;
import com.proficiency.view.home.view.SpecificView;

/**
 * @author linhuan on 2017/8/3 下午10:55
 */
public class SpecificFragment extends RefreshAndMoreFragment<SpecificView, SpecificPresenter, SpecificAdapter> implements SpecificView {

    private static final String TITLE = "title";

    private boolean isFirstOpen = true;
    private String titleStr = "";

    public static SpecificFragment newInstance(String title, int position) {
        Bundle bundle = new Bundle();
        bundle.putString(TITLE, title);
        bundle.putBoolean(INTENT_BOOLEAN_LAZYLOAD, 0 != position);
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
    protected void initView() {
        super.initView();

        rcvContent.addItemDecoration(new RecycleViewDivider(getContext(), LinearLayoutManager.HORIZONTAL, ResourceHelper.Dp2Px(0.5F), getResources().getColor(R.color.text_small_color)));
        rcvContent.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void SimpleOnItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                if (i < getBaseAdapter().getData().size()) {
                    DetailActivity.startDetailActivity(getActivity(), getBaseAdapter().getData().get(i));
                }
            }
        });
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
        getBasePresenter().getListData(titleStr, page, pageNum, isFirstOpen);
        isFirstOpen = false;
    }

    @Override
    public void setListDataFail() {
        super.setListDataFail();
        isFirstOpen = true;
    }
}
