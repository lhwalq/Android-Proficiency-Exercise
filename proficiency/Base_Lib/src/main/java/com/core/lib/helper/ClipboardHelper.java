package com.core.lib.helper;

import android.content.ClipboardManager;
import android.content.Context;

/**
 * @author linhuan on 2016/10/21 上午11:07
 */
public class ClipboardHelper {

    /**
     * 实现文本复制功能
     * add by wangqianzhou
     * @param content
     */
    public static void copy(String content, Context context) {
        // 得到剪贴板管理器
        ClipboardManager cmb = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        cmb.setText(content.trim());
    }

    /**
     * 实现粘贴功能
     * add by wangqianzhou
     * @param context
     * @return
     */
    public static String paste(Context context) {
        // 得到剪贴板管理器
        ClipboardManager cmb = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        if (Helper.isNotEmpty(cmb) && Helper.isNotEmpty(cmb.getText()) && Helper.isNotEmpty(cmb.getText().toString())) {
            return cmb.getText().toString().trim();
        } else {
            return "";
        }
    }

}
