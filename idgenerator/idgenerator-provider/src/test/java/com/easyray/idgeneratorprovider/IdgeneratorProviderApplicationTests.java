package com.easyray.idgeneratorprovider;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.easyray.idgeneratorapi.entity.IdSequence;
import com.easyray.idgeneratorapi.provider.IdService;
import com.easyray.idgeneratorprovider.mapper.IdSequenceMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class IdgeneratorProviderApplicationTests {

    @Autowired
    private IdService idService;
    @Resource
    private IdSequenceMapper idSequenceMapper;

    @Test
    void nextId() {
        for (int i = 0; i < 10; i++) {
            long id = idService.nextId("aaa");
            assert id > 0;
        }
        idSequenceMapper.delete(new QueryWrapper<IdSequence>().eq("class_name", "aaa"));
    }

    @Test
    void testQPS() {
        long current = System.currentTimeMillis();
        long id1 = idService.nextId("aaa");
        System.out.println("id1 = " + id1);
        while (System.currentTimeMillis() - current < 1000) {
            idService.nextId("aaa");
        }
        long id2 = idService.nextId("aaa");
        System.out.println("id2-id1 = " + (id2 - id1));


    }

}
