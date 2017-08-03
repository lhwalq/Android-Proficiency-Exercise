package com.proficiency;

import com.core.lib.base.BaseApplication;
import com.proficiency.basics.net.OkHttpHelper;

/**
 * @author linhuan on 2016/10/19 上午10:20
 */
public class ProficiencyApplication extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();

        OkHttpHelper.getInstance().initOkHttp();
    }

}
