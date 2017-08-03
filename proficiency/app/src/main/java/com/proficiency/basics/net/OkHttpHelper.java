package com.proficiency.basics.net;

import android.app.Activity;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.alibaba.fastjson.JSON;
import com.core.lib.helper.Helper;
import com.core.lib.helper.NetWorkHelper;
import com.core.lib.helper.ResourceHelper;
import com.proficiency.R;
import com.proficiency.bean.BaseDataBean;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.BitmapCallback;
import com.zhy.http.okhttp.callback.FileCallBack;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.File;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.OkHttpClient;

/**
 * @author linhuan on 2016/12/8 下午10:12
 */
public class OkHttpHelper {

    private static OkHttpHelper instance;

    public static OkHttpHelper getInstance() {
        if (Helper.isNull(instance)) {
            synchronized (OkHttpHelper.class) {
                if (Helper.isNull(instance)) {
                    instance = new OkHttpHelper();
                }
            }
        }
        return instance;
    }

    public void initOkHttp() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                .addInterceptor(new LoggerInterceptor("TAG"))
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                //其他配置
                .build();

        OkHttpUtils.initClient(okHttpClient);
    }

    public boolean isHasNet(DataInterface dataInterface) {
        if (NetWorkHelper.isNetworkAvailable()) {
            return true;
        } else {
            if (Helper.isNotEmpty(dataInterface)) {
                dataInterface.error(ResourceHelper.getString(R.string.text_net_no), null);
            }
            return false;
        }
    }

    /**
     * get提交
     *
     * @param url
     * @param getDataMap
     * @param dataInterface
     */
    public void getHtmlOkHttp(String url, Map<String, String> getDataMap, DataInterface dataInterface) {
        if (isHasNet(dataInterface)) {
            OkHttpUtils
                    .get()
                    .url(url)
                    .params(getDataMap)
                    .build()
                    .execute(new CommonHtmlCallback(dataInterface));
        }
    }

    /**
     * get提交
     *
     * @param url
     * @param getDataMap
     * @param dataInterface
     */
    public void getOkHttp(String url, Map<String, String> getDataMap, DataInterface dataInterface) {
        if (isHasNet(dataInterface)) {
            OkHttpUtils
                    .get()
                    .url(url)
                    .params(getDataMap)
                    .build()
                    .execute(new CommonDataCallback(dataInterface));
        }
    }

    /**
     * post提交
     *
     * @param url
     * @param getDataMap
     * @param dataInterface
     */
    public void postOkHttp(String url, Map<String, String> getDataMap, DataInterface dataInterface) {
        if (isHasNet(dataInterface)) {
            OkHttpUtils
                    .post()
                    .url(url)
                    .params(getDataMap)
                    .build()
                    .execute(new CommonDataCallback(dataInterface));
        }
    }

    /**
     * 提交文件
     *
     * @param url
     * @param file
     * @param dataInterface
     */
    public void postFileOkHttp(String url, File file, DataInterface dataInterface) {
        if (isHasNet(dataInterface)) {
            OkHttpUtils
                    .postFile()
                    .url(url)
                    .file(file)
                    .build()
                    .execute(new CommonDataCallback(dataInterface));
        }
    }

    /**
     * 提交文件
     *
     * @param url
     * @param file
     * @param getDataMap
     * @param dataInterface
     */
    public void postFileOkHttp(String url, File file, Map<String, String> getDataMap, DataInterface dataInterface) {
        if (isHasNet(dataInterface)) {
            OkHttpUtils
                    .post()
                    .addFile("icon", "icon.jpg", file)
                    .url(url)
                    .params(getDataMap)
                    .build()
                    .execute(new CommonDataCallback(dataInterface));
        }
    }

    /**
     * 下载文件
     *
     * @param url
     * @param fileCallBack
     */
    public void downloadOkHttp(String url, FileCallBack fileCallBack) {
        OkHttpUtils
                .get()
                .url(url)
                .build()
                .execute(fileCallBack);
    }

    /**
     * 显示图片
     *
     * @param url
     * @param bitmapCallback
     */
    public void showBitmapOkHttp(String url, BitmapCallback bitmapCallback) {
        OkHttpUtils
                .get()
                .url(url)
                .build()
                .execute(bitmapCallback);
    }

    /**
     * 显示图片
     *
     * @param url
     * @param imageView
     */
    public void showBitmapOkHttp(String url, final ImageView imageView) {
        OkHttpUtils
                .get()
                .url(url)
                .build()
                .execute(new BitmapCallback() {
                    @Override
                    public void onError(Call call, Exception e, int i) {

                    }

                    @Override
                    public void onResponse(Bitmap bitmap, int i) {
                        imageView.setImageBitmap(bitmap);
                    }
                });
    }

    /**
     * 取消请求
     *
     * @param activity
     */
    public void cancelOkHttp(Activity activity) {
        OkHttpUtils.get().tag(activity).build();
    }

    /**
     * 取消请求
     *
     * @param url
     */
    public void cancelOkHttp(String url) {
        OkHttpUtils.get().url(url).build().cancel();
    }

    public interface DataInterface {

        void error(String tip, Exception e);

        void success(String data);

    }

    public class CommonHtmlCallback extends StringCallback {

        private DataInterface dataInterface;

        public CommonHtmlCallback(DataInterface dataInterface) {
            this.dataInterface = dataInterface;
        }

        @Override
        public void onError(Call call, Exception e, int id) {
            if (Helper.isNotEmpty(dataInterface)) {
                dataInterface.error(ResourceHelper.getString(R.string.text_net_no), e);
            }
        }

        @Override
        public void onResponse(String response, int id) {
            if (Helper.isNotEmpty(dataInterface) && Helper.isNotEmpty(response)) {
                dataInterface.success(response);
            }
        }
    }

    public class CommonDataCallback extends StringCallback {

        private DataInterface dataInterface;

        public CommonDataCallback(DataInterface dataInterface) {
            this.dataInterface = dataInterface;
        }

        @Override
        public void onError(Call call, Exception e, int id) {
            if (Helper.isNotEmpty(dataInterface)) {
                dataInterface.error(ResourceHelper.getString(R.string.text_net_no), e);
            }
        }

        @Override
        public void onResponse(String response, int id) {
            if (Helper.isNotEmpty(dataInterface) && Helper.isNotEmpty(response)) {
                BaseDataBean baseDataBean = JSON.parseObject(response, BaseDataBean.class);
                if (Helper.isNotEmpty(baseDataBean)) {
                    if (baseDataBean.isError()) {
                        dataInterface.error(ResourceHelper.getString(R.string.text_net_no), null);
                    } else {
                        dataInterface.success(response);
                    }
                } else {
                    dataInterface.error(ResourceHelper.getString(R.string.text_net_no), null);
                }
            }
        }
    }

}
