package com.easyray.idgeneratorprovider.service.impl;

import com.easyray.idgeneratorapi.entity.IdSequence;
import com.easyray.idgeneratorapi.provider.IdSequenceLocalProvider;
import com.easyray.idgeneratorapi.provider.IdService;
import org.apache.dubbo.config.annotation.DubboService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Date: 20-2-4
 * @Author: wyy
 */
@DubboService
@Service
public class IdServiceImpl implements IdService {
    private final Logger logger = LoggerFactory.getLogger(IdServiceImpl.class);

    @Value("${id.skip-num}")
    private long skipNum;
    private final long minId = 1L;
    private Map<String, Long> className_max_map = new ConcurrentHashMap<>();
    private Map<String, Long> className_current_map = new ConcurrentHashMap<>();

    @Autowired
    private IdSequenceLocalProvider idSequenceLocalProvider;
    @Autowired
    private IdService idService;

    private long initId = 1;


    @PostConstruct
    private void init() {
        logger.debug("IdServiceImpl.init");
        IdSequence idSequence = idSequenceLocalProvider.fetchById(initId);
        if (idSequence == null) {
            idSequence = new IdSequence(initId)
                    .setClassName(IdSequence.class.getName())
                    .setValue(initId);
            idSequenceLocalProvider.save(idSequence);
        }

        List<IdSequence> idSequenceList = idSequenceLocalProvider.list();
        for (IdSequence sequence : idSequenceList) {
            long max = sequence.getValue() + skipNum;
            sequence.setValue(max);
            idSequenceLocalProvider.saveOrUpdate(sequence);
            className_max_map.put(sequence.getClassName(), max);
            className_current_map.put(sequence.getClassName(), sequence.getValue());
        }
    }

    @Override
    public synchronized long nextId(String className) {
        Long currentId = className_current_map.get(className);
        long returnId;
        if (currentId == null) {//entityName第一次生成id，需要写入数据库，写入map
            returnId = minId;
            IdSequence idSequence = new IdSequence(idService.nextId(IdSequence.class.getName()));
            idSequence.setClassName(className)
                    .setValue(minId + skipNum);
            idSequenceLocalProvider.save(idSequence);
            className_max_map.put(className, returnId + skipNum);
            className_current_map.put(className, returnId);
        } else {//已经写入过数据库
            returnId = currentId + 1;
            className_current_map.put(className, returnId);
            if (currentId.equals(className_max_map.get(className))) {//已经达到极限值，需要重新写入数据库,写入map

                IdSequence idSequence = idSequenceLocalProvider.fetchByClassName(className);
                idSequence.setValue(currentId + skipNum);
                idSequenceLocalProvider.updateById(idSequence);

                className_max_map.put(className, idSequence.getValue());
            }
        }
        return returnId;
    }
}
