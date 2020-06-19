package com.easyray.fastdfsprovider;

import com.easyray.auth.service.impl.SpringSecurityUtil;
import com.easyray.coreapi.entity.User;
import com.easyray.dfsapi.DFSClient;
import com.easyray.fastdfsprovider.constant.MetaDataConstant;
import com.github.tobato.fastdfs.domain.fdfs.MetaData;
import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.domain.proto.storage.DownloadByteArray;
import com.github.tobato.fastdfs.domain.upload.FastImageFile;
import com.github.tobato.fastdfs.domain.upload.ThumbImage;
import com.github.tobato.fastdfs.exception.FdfsUnsupportStorePathException;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;


/**
 * @Date: 20-3-19
 * @Author: wyy
 */
@Component
public class FastDFSClient implements DFSClient {

    private Logger log = LoggerFactory.getLogger(FastDFSClient.class);

    @Autowired
    private FastFileStorageClient storageClient;

    @Autowired
    private SpringSecurityUtil springSecurityUtil;

    /**
     * 上传文件
     *
     * @param file 文件对象
     * @return 文件访问地址
     * @throws IOException
     */
    @Override
    public String uploadFile(MultipartFile file) throws IOException {
        StorePath storePath = storageClient.uploadFile(file.getInputStream(), file.getSize(), FilenameUtils.getExtension(file.getOriginalFilename()), initMetaData(file.getOriginalFilename()));
        return storePath.getFullPath();
    }

    /**
     * 上传文件
     *
     * @param file 文件对象
     * @return 文件访问地址
     * @throws IOException
     */
    @Override
    public String uploadFile(File file) throws IOException {
        FileInputStream inputStream = new FileInputStream(file);
        StorePath storePath = storageClient.uploadFile(inputStream, file.length(), FilenameUtils.getExtension(file.getName()), initMetaData(file.getName()));
        return storePath.getFullPath();
    }

    /**
     * 将一段字符串生成一个文件上传
     *
     * @param content  文件内容
     * @param fileName
     * @return
     */
    @Override
    public String uploadFile(String content, String fileName) {
        byte[] buff = content.getBytes(StandardCharsets.UTF_8);
        ByteArrayInputStream stream = new ByteArrayInputStream(buff);
        StorePath storePath = storageClient.uploadFile(stream, buff.length, FilenameUtils.getExtension(fileName), initMetaData(fileName));
        return storePath.getFullPath();
    }

    /**
     * 下载文件
     *
     * @param fileUrl 文件url
     * @return
     */
    @Override
    public byte[] download(String fileUrl) {
        String tenant = fileUrl.substring(0, fileUrl.indexOf("/"));
        String path = fileUrl.substring(fileUrl.indexOf("/") + 1);
        byte[] bytes = storageClient.downloadFile(tenant, path, new DownloadByteArray());
        return bytes;
    }

    /**
     * 删除文件
     *
     * @param fileUrl 文件访问地址
     * @return
     */
    @Override
    public void deleteFile(String fileUrl) {
        if (StringUtils.isEmpty(fileUrl)) {
            return;
        }
        try {
            StorePath storePath = StorePath.parseFromUrl(fileUrl);
            storageClient.deleteFile(storePath.getGroup(), storePath.getPath());
        } catch (FdfsUnsupportStorePathException e) {
            log.warn(e.getMessage());
        }
    }

    @Override
    public String uploadImageAndCrtThumbImage(MultipartFile multipartFile) throws IOException {
        StorePath storePath = storageClient.uploadImageAndCrtThumbImage(multipartFile.getInputStream(), multipartFile.getSize(), FilenameUtils.getExtension(multipartFile.getOriginalFilename()), initMetaData(multipartFile.getOriginalFilename()));
        return storePath.getFullPath();
    }

    @Override
    public String uploadImageAndCrtThumbImage(MultipartFile multipartFile, int width, int height) throws IOException {
        StorePath storePath = storageClient.uploadImage(new FastImageFile(multipartFile.getInputStream(), multipartFile.getSize(), FilenameUtils.getExtension(multipartFile.getOriginalFilename()), initMetaData(multipartFile.getOriginalFilename()), new ThumbImage(width, height)));
        return storePath.getFullPath();
    }

    private Set<MetaData> initMetaData(String fileName) {

        Set<MetaData> set = new HashSet<>();
        set.add(new MetaData(MetaDataConstant.fileName, fileName));
        set.add(new MetaData(MetaDataConstant.creatDate, DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss")));
        User user = springSecurityUtil.getOrSetUser();
        if (user != null) {
            set.add(new MetaData(MetaDataConstant.userId, String.valueOf(user.getId())));
            set.add(new MetaData(MetaDataConstant.fullName, user.getFullName()));
        }
        return set;
    }

    /**
     * group1/M00/00/00/wKgKW17skrOAZp19AAEfWT2lLWo608.jpg
     *
     * @param url
     * @return
     */
    @Override
    public Map<String, Object> getMetaData(String url) {
        int i = url.indexOf("/");
        String group = url.substring(0, i);

        url = url.substring(i + 1);

        i = url.indexOf("/");
        String path = url.substring(i + 1);
        Set<MetaData> metaDataSet = storageClient.getMetadata(group, path);
        Map<String, Object> map = new HashMap<>();
        metaDataSet.forEach(metaData -> map.put(metaData.getName(), metaData.getValue()));
        return map;
    }
}
