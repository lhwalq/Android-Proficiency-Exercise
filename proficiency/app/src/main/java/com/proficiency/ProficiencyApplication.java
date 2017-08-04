package com.proficiency;

import com.core.lib.base.BaseApplication;
import com.proficiency.app.SystemDirectory;
import com.proficiency.basics.net.OkHttpHelper;
import com.proficiency.db.DbBusiness;

/**
 * @author linhuan on 2016/10/19 上午10:20
 */
public class ProficiencyApplication extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();

        SystemDirectory.initDirectory(getInstance());
        OkHttpHelper.getInstance().initOkHttp();
        DbBusiness.getInstance().initDatabase(getInstance());
    }

}
