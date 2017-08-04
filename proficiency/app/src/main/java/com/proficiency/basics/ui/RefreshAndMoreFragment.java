package com.proficiency.basics.ui;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.core.lib.base.mvp.BasePresenter;
import com.core.lib.helper.Helper;
import com.proficiency.basics.bean.BaseAdapter;
import com.proficiency.basics.bean.RefreshAndMoreView;

import java.util.List;

/**
 * @author linhuan on 2017/5/18 下午11:41
 */
public abstract class RefreshAndMoreFragment<V extends RefreshAndMoreView, P extends BasePresenter, B extends BaseAdapter> extends AppLazyFragment<V, P> implements SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener, RefreshAndMoreView {

    private static final int PAGE_NUM = 10;

    protected int page = 1, pageNum;
    protected boolean isFirst = true;

    protected SwipeRefreshLayout mrlContent;
    protected RecyclerView rcvContent;

    protected B baseAdapter;

    @Override
    protected void initView() {
        super.initView();

        baseAdapter = setBaseAdapter();
        pageNum = setPageNum();

        mrlContent = findView(setSwipeRefreshLayoutId());
        mrlContent.setOnRefreshListener(this);

        rcvContent = findView(setRecyclerViewId());
        rcvContent.setLayoutManager(getRecyclerViewManager());
        if (Helper.isNotNull(baseAdapter)) {
            baseAdapter.setOnLoadMoreListener(this);
            rcvContent.setAdapter(baseAdapter);
        }
    }

    @Override
    protected void initData() {
        super.initData();

        loadData(page, PAGE_NUM);
    }

    protected abstract B setBaseAdapter();

    public B getBaseAdapter() {
        return baseAdapter;
    }

    protected RecyclerView.LayoutManager getRecyclerViewManager() {
        return new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
    }

    @Override
    public void setListDataSucc(List dataList) {
        if (isFirst) {
            baseAdapter.setNewData(dataList);
            isFirst = false;
        } else {
            baseAdapter.addData(dataList);
        }
        if (PAGE_NUM > dataList.size()) {
            baseAdapter.loadComplete();
        } else {
            baseAdapter.hiedLoadingMore();
        }
        mrlContent.setRefreshing(false);
    }

    @Override
    public void setListDataFail() {
        page--;
        if (page < 1) {
            page = 1;
        }
        mrlContent.setRefreshing(false);
    }

    protected int setPageNum() {
        return PAGE_NUM;
    }

    protected abstract int setSwipeRefreshLayoutId();

    protected abstract int setRecyclerViewId();

    @Override
    public void onRefresh() {
        isFirst = true;
        page = 1;
        loadData(page, PAGE_NUM);
    }

    @Override
    public void onLoadMoreRequested() {
        page++;
        loadData(page, PAGE_NUM);
    }

    protected abstract void loadData(int page, int pageNum);

}
