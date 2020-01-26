package com.wyy.codegen

import com.baomidou.mybatisplus.generator.AutoGenerator
import com.baomidou.mybatisplus.generator.config.DataSourceConfig
import com.baomidou.mybatisplus.generator.config.GlobalConfig
import com.baomidou.mybatisplus.generator.config.PackageConfig
import com.baomidou.mybatisplus.generator.config.StrategyConfig
import org.junit.Test

/**
 * @Date: 20-1-26
 * @Author: wyy
 */
class MybatisGen {
    @Test
    void userGen() {
        gen('/home/wyy/github/easyry/user/user-service/src/main/java', 'com.wyy.userservice', 'sys_', 'sys_user')
    }

    private void gen(String path, String packageName, String tablePrefix, String... tableName) {
        AutoGenerator autoGenerator = new AutoGenerator();
        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig.setOutputDir(path);
        globalConfig.setAuthor(System.getProperty('user.name'));
        globalConfig.setOpen(false);

        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setUrl("jdbc:mysql://localhost/easyray?useUnicode=true&useSSL=false&characterEncoding=utf8");
        dataSourceConfig.setDriverName("com.mysql.cj.jdbc.Driver");
        dataSourceConfig.setUsername("easyray");
        dataSourceConfig.setPassword("easyray");

        PackageConfig packageConfig = new PackageConfig();
        packageConfig.setParent(packageName);

        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig.setInclude(tableName);
        strategyConfig.setTablePrefix(tablePrefix);

        autoGenerator.setGlobalConfig(globalConfig);
        autoGenerator.setDataSource(dataSourceConfig);
        autoGenerator.setPackageInfo(packageConfig);
        autoGenerator.setStrategy(strategyConfig);

        autoGenerator.execute();
    }
}
