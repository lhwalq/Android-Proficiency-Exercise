package com.core.lib.base.mvp;

import java.lang.ref.WeakReference;

/**
 * Created by wjc on 2017/4/5.
 */
public class AppPresenter<V extends BaseView> implements BasePresenter<V> {

    private WeakReference<V> viewRef;

    @Override
    public void attachView(V view) {
        viewRef = new WeakReference<V>(view);
    }

    public V getView() {
        return viewRef == null ? null : viewRef.get();
    }

    public boolean isViewAttached() {
        return viewRef != null && viewRef.get() != null;
    }

    @Override
    public void detachView() {
        if (viewRef != null) {
            viewRef.clear();
            viewRef = null;
        }
    }
}
