package com.easyray.dfsapi;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Map;

/**
 * @Date: 20-3-24
 * @Author: wyy
 */
public interface DFSClient {

    String uploadFile(MultipartFile file) throws IOException;

    String uploadFile(File file) throws IOException;

    /**
     * @param content
     * @param fileName 包含后缀
     * @return
     */
    String uploadFile(String content, String fileName);

    byte[] download(String fileUrl);

    void deleteFile(String fileUrl);

    String uploadImageAndCrtThumbImage(MultipartFile multipartFile) throws IOException;

    String uploadImageAndCrtThumbImage(MultipartFile multipartFile, int width, int height) throws IOException;

    Map<String, Object> getMetaData(String url);
}
