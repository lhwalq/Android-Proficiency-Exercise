package com.proficiency.basics.ui;

import android.view.View;
import android.view.ViewStub;

import com.core.lib.base.BaseActionBarActivity;
import com.core.lib.helper.Helper;

/**
 * AppActionBarActivity:基类
 * 
 * @author linhuan 2015年7月21日上午9:09:34
 */
public abstract class AppActionBarActivity extends BaseActionBarActivity {

    public void showViewStub(int rootId) {
        ViewStub stub = (ViewStub) findViewById(rootId);
        if (Helper.isNotNull(stub)) {
            stub.inflate();
        }
    }

    public View getViewStub(int rootId, int childId) {
        ViewStub stub = (ViewStub) findViewById(rootId);
        return stub.inflate().findViewById(childId);
    }
	
}

