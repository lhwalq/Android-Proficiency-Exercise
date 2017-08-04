package com.core.lib.base.mvp;

/**
 * @author linhuan on 2017/8/3 下午10:36
 */
public interface BasePresenter<V extends BaseView> {

    void attachView(V view);

    void detachView();

}
