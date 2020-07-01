package com.easyray.documentapi.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.easyray.baseapi.entity.TenantEntity;
import com.easyray.documentapi.util.FileVersionUtil;
import com.wyy.actable.annotation.Column;
import com.wyy.actable.annotation.Table;

import static com.wyy.actable.constants.MySqlDataType.BIGINT;
import static com.wyy.actable.constants.MySqlDataType.VARCHAR;

/**
 * @author wyy
 * @since 2020-02_13
 */
@TableName("sys_dfile")
@Table(name = "sys_dfile")
public class DFile extends TenantEntity<Long> {

    @Column(name = "name", type = VARCHAR, length = 20, nullable = false)
    private String name;

    @Column(name = "extension", type = VARCHAR, length = 10, nullable = false)
    private String extension;

    @Column(name = "media_type", type = VARCHAR, length = 10, nullable = false)
    private String mediaType;

    @Column(name = "folder_id", type = BIGINT, length = 10, nullable = false)
    private long folderId;

    @Column(name = "folder_path", type = VARCHAR, length = 75, nullable = false)
    private String folderPath;

    @Column(name = "url", type = VARCHAR, length = 75, nullable = false)
    private String url;

    /**
     * 因为版本不仅可以是1.0还可以是1.0.0，还可以是日期作为版本，所以这里只能使用String类型
     */
    @Column(name = "version", type = VARCHAR, length = 20, nullable = false, defaultValue = "1")
    private String version = FileVersionUtil.defaultVersion;

    public String getUrl() {
        return url;
    }

    @Override
    public String toString() {
        return "DFile{" +
                "name='" + name + '\'' +
                ", extension='" + extension + '\'' +
                ", mediaType='" + mediaType + '\'' +
                ", folderId=" + folderId +
                ", folderPath='" + folderPath + '\'' +
                ", url='" + url + '\'' +
                ", version='" + version + '\'' +
                "} " + super.toString();
    }

    public String getVersion() {
        return version;
    }

    public DFile setVersion(String version) {
        this.version = version;
        return this;
    }

    public DFile setUrl(String url) {
        this.url = url;
        return this;
    }

    public DFile(Long id) {
        super(id);
    }

    public DFile() {
    }

    public String getName() {
        return name;
    }

    public DFile setName(String name) {
        this.name = name;
        return this;
    }

    public String getExtension() {
        return extension;
    }

    public DFile setExtension(String extension) {
        this.extension = extension;
        return this;
    }

    public String getMediaType() {
        return mediaType;
    }

    public DFile setMediaType(String mediaType) {
        this.mediaType = mediaType;
        return this;
    }

    public long getFolderId() {
        return folderId;
    }

    public DFile setFolderId(long folderId) {
        this.folderId = folderId;
        return this;
    }

    public String getFolderPath() {
        return folderPath;
    }

    public DFile setFolderPath(String folderPath) {
        this.folderPath = folderPath;
        return this;
    }
}
