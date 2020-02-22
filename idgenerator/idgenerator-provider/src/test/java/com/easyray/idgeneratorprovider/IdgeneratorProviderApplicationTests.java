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

}
