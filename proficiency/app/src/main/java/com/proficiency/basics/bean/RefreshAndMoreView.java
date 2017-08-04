package com.proficiency.basics.bean;

import com.core.lib.base.mvp.BaseView;

import java.util.List;

/**
 * @author linhuan on 2017/8/3 下午11:27
 */
public interface RefreshAndMoreView<T extends BaseJson> extends BaseView {

    void setListDataFail();

    void setListDataSucc(List<T> dataList);

}
