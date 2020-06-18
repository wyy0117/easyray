package com.easyray.common.util;

import org.springframework.web.multipart.MultipartFile;

/**
 * @Date: 2020/6/18
 * @Author: wyy
 */
public class FileUtil {

    public static String getExtension(String fileName) {
        int index = fileName.lastIndexOf(".");
        if (index > 0) {
            return fileName.substring(index + 1, fileName.length());
        }
        return fileName;
    }

    public static String getExtension(MultipartFile multipartFile) {
        return getExtension(multipartFile.getOriginalFilename());
    }
}
