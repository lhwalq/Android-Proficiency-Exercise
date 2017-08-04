package com.proficiency.view.home.adapter;

import android.view.ViewGroup;

import com.alibaba.fastjson.JSON;
import com.brioal.brioalbanner.entity.BannerEntity;
import com.brioal.brioalbanner.view.Banner;
import com.chad.library.adapter.base.BaseViewHolder;
import com.core.lib.helper.DeviceHelper;
import com.core.lib.helper.Helper;
import com.core.lib.helper.ResourceHelper;
import com.core.lib.helper.TimeHelper;
import com.proficiency.R;
import com.proficiency.basics.bean.BaseAdapter;
import com.proficiency.bean.resp.SpecificBean;

import java.util.ArrayList;
import java.util.List;

/**
 * @author linhuan on 2017/8/3 下午11:45
 */
public class SpecificAdapter extends BaseAdapter<SpecificBean> {

    private static final int NO_IMG = 1;
    private static final int HAS_IMG = 2;

    private int imageHeight, imageWidth;

    public SpecificAdapter() {
        super(R.layout.item_soecific_one);

        imageWidth = DeviceHelper.getScreenWidth();
        imageHeight = imageWidth * 190 / 360;
    }

    @Override
    protected BaseViewHolder onCreateDefViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {

            case NO_IMG:
                this.mLayoutResId = R.layout.item_soecific_one;
                break;

            case HAS_IMG:
                this.mLayoutResId = R.layout.item_soecific_two;
                break;

        }
        return super.onCreateDefViewHolder(parent, viewType);
    }

    @Override
    public int getItemViewType(int position) {
        if (position < getData().size()) {
            SpecificBean specificBean = getData().get(position);
            if (null != specificBean) {
                return Helper.isEmpty(specificBean.getImages()) ? NO_IMG : HAS_IMG;
            }
        }
        return super.getItemViewType(position);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, SpecificBean specificBeanBaseDataBean) {
        if (null != specificBeanBaseDataBean) {
            switch (baseViewHolder.getItemViewType()) {

                case NO_IMG:
                    baseViewHolder.setText(R.id.tv_desc, specificBeanBaseDataBean.getDesc());
                    baseViewHolder.setText(R.id.tv_user_name, specificBeanBaseDataBean.getWho());
                    baseViewHolder.setText(R.id.tv_time, TimeHelper.timeConversion(specificBeanBaseDataBean.getCreatedAt(), TimeHelper.YYYYMMDDHHMMSSSSSS, TimeHelper.YYYYMMDDTWO));
                    break;

                case HAS_IMG:
                    List<BannerEntity> mList = new ArrayList<>();
                    // 获取图片地址
                    List<String> imageList = JSON.parseArray(specificBeanBaseDataBean.getImages(), String.class);
                    int size = imageList.size();
                    for (int i = 0; i < size; i++) {
                        mList.add(new BannerEntity(i + 1, ResourceHelper.getString(R.string.image_width_and_height, imageList.get(i), imageWidth, imageHeight)));
                    }
                    Banner banner = baseViewHolder.getView(R.id.banner);
                    banner.getLayoutParams().height = imageHeight;
                    banner.setList(mList, specificBeanBaseDataBean.getDesc(), specificBeanBaseDataBean.getWho(), TimeHelper.timeConversion(specificBeanBaseDataBean.getCreatedAt(), TimeHelper.YYYYMMDDHHMMSSSSSS, TimeHelper.YYYYMMDDTWO));
                    break;

            }
        }
    }
}
