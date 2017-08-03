package com.proficiency.basics.bean;

import com.core.lib.base.mvp.BaseView;

/**
 * @author linhuan on 2017/8/3 下午11:27
 */
public interface RefreshAndMoreView extends BaseView {

    void getListDataFail();

    void getListDataSucc();

}
