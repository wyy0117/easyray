package com.easyray.documentapi.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.wyy.actable.annotation.Column;
import com.wyy.actable.annotation.Table;
import com.wyy.actable.constants.MySqlDataType;
import org.springframework.beans.BeanUtils;

/**
 * @Date: 2020/6/18
 * @Author: wyy
 */

/**
 * 一个文件可以有多个文件版本
 */
@Table(name = "sys_dfile_version")
@TableName("sys_dfile_version")
public class DFileVersion extends DFile {

    @Column(name = "file_id", type = MySqlDataType.BIGINT, length = 20, nullable = false)
    private long fileId;

    @Column(name = "change_log", type = MySqlDataType.VARCHAR, length = 75, nullable = true)
    private String changeLog;

    public DFileVersion(long id, DFile dFile) {
        super(id);
        this.fileId = dFile.getId();
        BeanUtils.copyProperties(dFile, this, "id");
    }

    public DFileVersion(DFile dFile) {
        this.fileId = dFile.getId();
        BeanUtils.copyProperties(dFile, this, "id");
    }

    @Override
    public String toString() {
        return "DFileVersion{" +
                "fileId=" + fileId +
                "} " + super.toString();
    }

    public String getChangeLog() {
        return changeLog;
    }

    public DFileVersion setChangeLog(String changeLog) {
        this.changeLog = changeLog;
        return this;
    }

    public long getFileId() {
        return fileId;
    }

    public DFileVersion setFileId(long fileId) {
        this.fileId = fileId;
        return this;
    }
}
