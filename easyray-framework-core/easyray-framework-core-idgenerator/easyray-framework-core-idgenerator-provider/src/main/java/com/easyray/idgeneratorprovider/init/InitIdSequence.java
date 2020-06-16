package com.easyray.idgeneratorprovider.init;

import com.easyray.idgeneratorapi.entity.IdSequence;
import com.easyray.idgeneratorapi.provider.IdSequenceLocalProvider;
import com.easyray.idgeneratorapi.provider.IdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * @Date: 20-2-12
 * @Author: wyy
 */
@Component
public class InitIdSequence implements ApplicationRunner {

    @Autowired
    private IdSequenceLocalProvider idSequenceLocalProvider;
    @Autowired
    private IdService idService;

    private long initId = 1;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        IdSequence idSequence = idSequenceLocalProvider.fetchById(initId);
        if (idSequence == null) {
            idSequence = new IdSequence(initId)
                    .setClassName(IdSequence.class.getName())
                    .setValue(initId);
            idSequenceLocalProvider.save(idSequence);
            idService.init();
        }
    }
}
