package com.easyray.documentapi.util;

/**
 * @Date: 2020/6/19
 * @Author: wyy
 */
public class FileVersionUtil {

    public static final String defaultVersion = "1";

    public static String nextVersion(String version) {
        return String.valueOf(Long.parseLong(version) + 1);
    }
}
