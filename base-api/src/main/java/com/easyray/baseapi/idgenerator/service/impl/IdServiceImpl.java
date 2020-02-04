package com.easyray.baseapi.idgenerator.service.impl;

import com.easyray.baseapi.idgenerator.entity.IdSequence;
import com.easyray.baseapi.idgenerator.service.IdSequenceLocalService;
import com.easyray.baseapi.idgenerator.service.IdService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Date: 20-2-4
 * @Author: wyy
 */
@Component
public class IdServiceImpl implements IdService {
    private final Logger log = LoggerFactory.getLogger(IdServiceImpl.class);

    private final long skipNum = 1000L;
    private final long minId = 1L;
    private Map<String, Long> entityName_max_map = new ConcurrentHashMap<>();
    private Map<String, Long> entityName_current_map = new ConcurrentHashMap<>();

    @Autowired
    private IdSequenceLocalService idSequenceLocalService;

    @PostConstruct
    private void init() {
        log.debug("IdServiceImpl.init");
        List<IdSequence> idSequenceList = idSequenceLocalService.list();
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
            IdSequence idSequence = new IdSequence();
            idSequence.setEntityName(entityName)
                    .setValue(minId);
            idSequenceLocalService.save(idSequence);
            entityName_max_map.put(entityName, idSequence.getValue() + skipNum);
            entityName_current_map.put(entityName, idSequence.getValue());
            returnId = idSequence.getId();
        } else {//已经写入过数据库
            returnId = currentId + 1;
            entityName_current_map.put(entityName, returnId);
            if (currentId.equals(entityName_max_map.get(entityName))) {//已经达到极限值，需要重新写入数据库,写入map

                IdSequence idSequence = idSequenceLocalService.fetchByEntityName(entityName);
                idSequence.setValue(currentId + skipNum);
                idSequenceLocalService.updateById(idSequence);

                entityName_max_map.put(entityName, idSequence.getValue());
            }
        }
        return returnId;
    }
}