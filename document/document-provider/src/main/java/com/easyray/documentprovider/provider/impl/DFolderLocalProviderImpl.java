package com.easyray.documentprovider.provider.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.easyray.baseapi.provider.EasyrayServiceImpl;
import com.easyray.documentapi.entity.DFile;
import com.easyray.documentapi.entity.DFolder;
import com.easyray.documentapi.provider.DFileLocalProvider;
import com.easyray.documentapi.provider.DFolderLocalProvider;
import com.easyray.documentprovider.mapper.DFolderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * @author wyy
 * @since 2020-02_13
 */
@Service
@Component
public class DFolderLocalProviderImpl extends EasyrayServiceImpl<DFolderMapper, DFolder> implements DFolderLocalProvider {


    @Autowired
    @Qualifier("DFileLocalProviderImpl")
    private DFileLocalProvider dFileLocalProvider;

    @Override
    public void deleteFolder(long id) {
        dFileLocalProvider.remove(new QueryWrapper<DFile>().eq("folder_id", id));
        removeById(id);
    }
}
