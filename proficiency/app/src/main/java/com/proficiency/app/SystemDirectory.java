package com.proficiency.app;

import android.content.Context;
import android.os.Environment;

import java.io.File;

public class SystemDirectory {

    public static String BASE_DIR = Environment.getExternalStorageDirectory() + "/Proficiency";
    public static String SAVE_DIR = BASE_DIR + "/save/";
    public static String CACHE_DIR = BASE_DIR + "/cache/";
    public static String IMAGE_DIR = CACHE_DIR + "/image/";

    public static void initDirectory(Context ctx) {
        // 若SD卡不存在，则重置主要下载目录
        if (!Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            String private_file_dir = ctx.getFilesDir().getAbsolutePath();
            BASE_DIR = private_file_dir;
            CACHE_DIR = BASE_DIR + "/cache/";
            IMAGE_DIR = CACHE_DIR + "/image/";
        }
        mkdirs(CACHE_DIR);
        mkdirs(IMAGE_DIR);
        mkdirs(SAVE_DIR);
    }

    private static void mkdirs(String path) {
        try {
            File file = new File(path);
            if (!file.exists()) {
                file.mkdirs();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
