package com.easyray.documentprovider;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.easyray.documentapi.entity.DFile;
import com.easyray.documentprovider.mapper.MyMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Date: 20-2-14
 * @Author: wyy
 */
@SpringBootTest
public class TestMapper {

    @Resource
    private MyMapper myMapper;

    @Test
    public void test() {
        List<DFile> dFileList = myMapper.filterFindBy(new QueryWrapper<DFile>().eq("name", "123.txt"), 2);
        System.out.println("dFileList.size() = " + dFileList.size());
    }
}
