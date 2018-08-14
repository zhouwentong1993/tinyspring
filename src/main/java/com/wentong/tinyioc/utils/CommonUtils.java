package com.wentong.tinyioc.utils;

/**
 * 通用工具类
 */
public final class CommonUtils {

    private CommonUtils() {

    }

    public static String getLogString(String source, Object... replace) {
        String s = source.replaceAll("\\{}", "%s");
        return String.format(s, replace);
    }

}
