package com.easyray.idgeneratorprovider.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.easyray.idgeneratorapi.entity.IdSequence;
import com.easyray.idgeneratorapi.service.IdSequenceLocalProvider;
import com.easyray.idgeneratorapi.service.IdService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Date: 20-2-4
 * @Author: wyy
 */
@Service
@Component
@Transactional
public class IdServiceImpl implements IdService {
    private final Logger log = LoggerFactory.getLogger(IdServiceImpl.class);

    private final long skipNum = 1000L;
    private final long minId = 1L;
    private Map<String, Long> entityName_max_map = new ConcurrentHashMap<>();
    private Map<String, Long> entityName_current_map = new ConcurrentHashMap<>();

    @Autowired
    private IdSequenceLocalProvider idSequenceLocalProvider;

    @PostConstruct
    private void init() {
        log.debug("IdServiceImpl.init");
        List<IdSequence> idSequenceList = idSequenceLocalProvider.list();
        for (IdSequence idSequence : idSequenceList) {
            entityName_max_map.put(idSequence.getEntityName(), idSequence.getValue() + skipNum);
            entityName_current_map.put(idSequence.getEntityName(), idSequence.getValue());
        }
    }

    @Override
    public synchronized long nextId(String entityName) {
        Long currentId = entityName_current_map.get(entityName);
        long returnId;
        if (currentId == null) {//entityName第一次生成id，需要写入数据库，写入map
            IdSequence idSequence = new IdSequence(System.currentTimeMillis());
            idSequence.setEntityName(entityName)
                    .setValue(minId + skipNum);
            idSequenceLocalProvider.save(idSequence);
            entityName_max_map.put(entityName, idSequence.getValue() + skipNum);
            entityName_current_map.put(entityName, idSequence.getValue());
            returnId = idSequence.getValue();
        } else {//已经写入过数据库
            returnId = currentId + 1;
            entityName_current_map.put(entityName, returnId);
            if (currentId.equals(entityName_max_map.get(entityName))) {//已经达到极限值，需要重新写入数据库,写入map

                IdSequence idSequence = idSequenceLocalProvider.fetchByEntityName(entityName);
                idSequence.setValue(currentId + skipNum);
                idSequenceLocalProvider.updateById(idSequence);

                entityName_max_map.put(entityName, idSequence.getValue());
            }
        }
        return returnId;
    }
}
