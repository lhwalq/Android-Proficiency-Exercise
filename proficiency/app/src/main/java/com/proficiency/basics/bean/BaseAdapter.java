package com.proficiency.basics.bean;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;

/**
 * @author linhuan on 2017/8/3 下午11:43
 */
public abstract class BaseAdapter<T extends BaseJson> extends BaseQuickAdapter<T> {

    public BaseAdapter(int layoutResId) {
        super(layoutResId, new ArrayList<T>());
    }
}
