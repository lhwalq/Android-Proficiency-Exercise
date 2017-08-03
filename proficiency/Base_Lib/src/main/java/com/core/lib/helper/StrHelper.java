package com.core.lib.helper;

/**
 * @author linhuan on 2016/10/21 下午4:12
 */
public class StrHelper {

    public static int getCutOutInt(String oldStr, int start, int end) {
        String newStr = getCutOutStr(oldStr, start, end);
        try {
            return Integer.valueOf(newStr);
        } catch (Exception e) {
            return 0;
        }
    }

    public static String getCutOutStr(String oldStr, int start, int end) {
        int length = oldStr.length();
        return oldStr.substring(getLength(length, start, true), getLength(length, end, false));
    }

    public static int getLength(int length, int position, boolean isStart) {
        return (0 <= position && position < length) ? position : (isStart ? 0 : length);
    }

}
