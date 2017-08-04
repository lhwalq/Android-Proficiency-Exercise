package com.proficiency.common.widget;

import android.content.Context;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ProgressBar;

public class CommonChromeClient extends WebChromeClient {

    private Context mContext;
    private ProgressBar mProgressBar;

    public CommonChromeClient(Context context) {
        mContext = context;
    }

    public CommonChromeClient(Context context, ProgressBar progressBar) {
        mContext = context;
        mProgressBar = progressBar;
    }

    @Override
    public void onProgressChanged(WebView view, int newProgress) {
        // 如果有进度条，则显示进度条进度
        try {
            if (mProgressBar != null) {
                if (newProgress == 100) {
                    mProgressBar.setVisibility(View.GONE);
                } else {
                    if (mProgressBar.getVisibility() == View.GONE) {
                        mProgressBar.setVisibility(View.VISIBLE);
                    }
                    mProgressBar.setProgress(newProgress);
                }
            }
        } catch (Exception e) {

        }
        super.onProgressChanged(view, newProgress);
    }

}
