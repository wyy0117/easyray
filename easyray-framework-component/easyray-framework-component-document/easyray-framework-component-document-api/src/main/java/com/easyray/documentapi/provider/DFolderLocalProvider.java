package com.easyray.documentapi.provider;

import com.easyray.baseapi.provider.BaseLocalProvider;
import com.easyray.documentapi.entity.DFolder;

import java.util.List;

/**
 * @Date: 2020-02_13
 * @Author: wyy
 */
public interface DFolderLocalProvider extends BaseLocalProvider<DFolder> {


    /**
     * auto delete file and folder under folder
     *
     * @param id
     */
    void deleteFolder(long id);

    List<DFolder> getSubFolderList(long parentFolderId);

}
