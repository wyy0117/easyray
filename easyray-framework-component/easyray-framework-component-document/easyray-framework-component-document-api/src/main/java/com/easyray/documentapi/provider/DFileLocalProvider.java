package com.easyray.documentapi.provider;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.easyray.baseapi.provider.BaseLocalProvider;
import com.easyray.documentapi.entity.DFile;
import com.easyray.documentapi.entity.DFileVersion;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * @Date: 2020-02_13
 * @Author: wyy
 */
public interface DFileLocalProvider extends BaseLocalProvider<DFile> {

    IPage<DFile> findByName(IPage<DFile> page, String name, long tenantId);

    IPage<DFile> findByFolderId(IPage<DFile> page, long folderId, long tenantId);

    List<DFile> findByFolderId(long folderId, long tenantId);

    /**
     * 上传文件，并更新下载链接到dFile中
     *
     * @param dFile         需要自己给除url外的属性赋值
     * @param multipartFile 保存文件，并给dFile的url赋值
     * @return
     * @throws IOException
     */
    String uploadFile(DFile dFile, MultipartFile multipartFile) throws IOException;

    String updateFile(DFile dFile, MultipartFile multipartFile) throws IOException;

    /**
     * 更新文件
     *
     * @param dFile         要更新哪个文件，需要自己填好version字段,只会更新url地址
     * @param dFileVersion
     * @param multipartFile
     * @return
     * @throws IOException
     */
    String updateFile(DFile dFile, DFileVersion dFileVersion, MultipartFile multipartFile) throws IOException;

    String updateFile(DFile dFile, DFileVersion dFileVersion, String version, MultipartFile multipartFile) throws IOException;

    String updateFile(DFile dFile, DFileVersion dFileVersion, String version, MultipartFile multipartFile, String changeLog) throws IOException;
}
