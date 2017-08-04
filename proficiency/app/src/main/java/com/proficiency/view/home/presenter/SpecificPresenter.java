package com.proficiency.view.home.presenter;

import com.alibaba.fastjson.JSON;
import com.core.lib.base.mvp.AppPresenter;
import com.core.lib.helper.Helper;
import com.core.lib.helper.ResourceHelper;
import com.proficiency.R;
import com.proficiency.app.AppConfig;
import com.proficiency.basics.net.OkHttpHelper;
import com.proficiency.bean.resp.SpecificBean;
import com.proficiency.db.DbBusiness;
import com.proficiency.view.home.response.SpecificResponse;
import com.proficiency.view.home.view.SpecificView;

import java.util.List;


/**
 * @author linhuan on 2017/8/3 下午11:00
 */
public class SpecificPresenter extends AppPresenter<SpecificView> {

    public void getListData(final String title, final int page, int pageNum, final boolean isFirstOpen) {
        OkHttpHelper.getInstance().getOkHttp(
                ResourceHelper.getString(R.string.net_data_link, AppConfig.NetData.NET_HEADER, title, pageNum, page),
                null,
                new OkHttpHelper.DataInterface() {
                    @Override
                    public void error(String tip, Exception e) {
                        if (isFirstOpen) {
                            SpecificView specificView = getView();
                            if (null != specificView) {
                                // 需要判断是否有缓存数据
                                List<SpecificBean> specificBeanList = DbBusiness.getInstance().insertOrReplaceMenu(title);
                                if (Helper.isEmpty(specificBeanList)) {
                                    specificView.setListDataFail();
                                } else {
                                    specificView.setListDataSucc(specificBeanList);
                                }
                            }
                        }
                    }

                    @Override
                    public void success(String data) {
                        SpecificView specificView = getView();
                        if (null != specificView) {
                            SpecificResponse specificResponse = JSON.parseObject(data, SpecificResponse.class);
                            if (null != specificResponse) {
                                // 只缓存第一页数据
                                if (1 == page) {
                                    for (SpecificBean specificBean : specificResponse.getResults()) {
                                        specificBean.setTypeName(title);
                                    }
                                    DbBusiness.getInstance().insertOrReplaceMenu(specificResponse.getResults());
                                }
                                specificView.setListDataSucc(specificResponse.getResults());
                            }
                        }
                    }
                });
    }

}
