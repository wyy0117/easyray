package com.easyray.documentapi.provider;

import com.easyray.baseapi.provider.BaseLocalProvider;
import com.easyray.documentapi.entity.DFolder;

import java.io.Serializable;
import java.util.List;

/**
 * @Date: 2020-02_13
 * @Author: wyy
 */
public interface DFolderLocalProvider extends BaseLocalProvider<DFolder> {

    List<DFolder> getSubFolderList(Serializable parentFolderId);
}
