package com.proficiency.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.proficiency.bean.resp.SpecificBean;

import java.util.List;

public class DbBusiness {

    private final String DB_NAME = "Proficiency_DB";

    private static DbBusiness instance = null;

    private DaoMaster.DevOpenHelper mHelper;
    private SQLiteDatabase db;
    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;

    private DbBusiness() {
    }

    public static DbBusiness getInstance(){
        if(instance == null){
            synchronized (DbBusiness.class){
                if(instance == null){
                    instance = new DbBusiness();
                }
            }
        }
        return instance;
    }

    public void initDatabase(Context context){
        if (null == mHelper) {
            mHelper = new DaoMaster.DevOpenHelper(context, DB_NAME);
        }
        if (null == db) {
            db = mHelper.getWritableDatabase();
        }
        if (null == mDaoMaster) {
            mDaoMaster = new DaoMaster(db);
        }
        if (null == mDaoSession) {
            mDaoSession = mDaoMaster.newSession();
        }

    }

    public DaoSession getDaoSession() {
        return mDaoSession;
    }

    public void insertOrReplaceMenu(List<SpecificBean> specificBeanList) {
        SpecificBeanDao dao = getDaoSession().getSpecificBeanDao();
        dao.insertOrReplaceInTx(specificBeanList);
    }

    public List<SpecificBean> insertOrReplaceMenu(String title) {
        SpecificBeanDao dao = getDaoSession().getSpecificBeanDao();
        return dao.queryBuilder().where(SpecificBeanDao.Properties.TypeName.eq(title)).list();
    }

}
