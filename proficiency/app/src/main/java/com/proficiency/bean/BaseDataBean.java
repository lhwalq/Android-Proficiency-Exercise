package com.proficiency.bean;

import com.proficiency.basics.bean.BaseJson;

/**
 * @author linhuan on 2016/12/19 下午9:49
 */
public class BaseDataBean<T> extends BaseJson {

    private boolean error;
    private T results;

    public BaseDataBean() {

    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public T getResults() {
        return results;
    }

    public void setResults(T results) {
        this.results = results;
    }
}
