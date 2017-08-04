package com.brioal.brioalbanner.entity;

import java.io.Serializable;

/**ViewPager Content Entity
 * Created by Brioal on 2016/8/31.
 */

public class BannerEntity implements Serializable{

    private int mIndex;             // 序号
    private String mImageUrl;       // 图片链接

    public BannerEntity(int index, String imageUrl) {
        mIndex = index;
        mImageUrl = imageUrl;
    }

    public int getmIndex() {
        return mIndex;
    }

    public void setmIndex(int mIndex) {
        this.mIndex = mIndex;
    }

    public String getmImageUrl() {
        return mImageUrl;
    }

    public void setmImageUrl(String mImageUrl) {
        this.mImageUrl = mImageUrl;
    }
}
