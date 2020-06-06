#### 模块说明
1. easyray-framework-core  
    框架核心模块
1. easyray-framework-component  
    框架层组件
1. easyray-framework-util 
    框架层工具
1. scripts
    启动服务脚本
#### 框架使用介绍
框架设计为4层结构  
    - api层提供基础接口（interface）  
    - provider层提供基础service（dubbo），操作数据库，权限底层检查  
    - 业务层(consumer)做业务操作，提供service  
    - controller对外提供接口（rest service）  
#### 注意事项
1. 所有的依赖关系全部从根pom文件查找，禁止模块引入特殊依赖
#### 开发步骤
1. 创建api模块（maven）
1. 创建provider模块（springboot）
1. api模块添加依赖
    ```
    <dependency>
        <groupId>com.easyray</groupId>
        <artifactId>easyray-framework-core-baseapi</artifactId>
        <version>1.0.0</version>
    </dependency>
    ```
1. provider模块修改pom修改parent
1. provider模块修改依赖
    ```
    <dependency>
        <!-- Import dependency management from Spring Boot -->
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-dependencies</artifactId>
        <version>${springboot.version}</version>
        <type>pom</type>
        <scope>import</scope>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <scope>test</scope>
        <exclusions>
            <exclusion>
                <groupId>org.junit.vintage</groupId>
                <artifactId>junit-vintage-engine</artifactId>
            </exclusion>
        </exclusions>
    </dependency>

    <!-- https://mvnrepository.com/artifact/com.alibaba/druid -->
    <dependency>
        <groupId>com.alibaba</groupId>
        <artifactId>druid</artifactId>
    </dependency>

    <dependency>
        <groupId>com.alibaba.boot</groupId>
        <artifactId>dubbo-spring-boot-starter</artifactId>
        <exclusions>
            <exclusion>
                <artifactId>spring-boot-starter</artifactId>
                <groupId>org.springframework.boot</groupId>
            </exclusion>
        </exclusions>
    </dependency>

    <dependency>
        <groupId>com.baomidou</groupId>
        <artifactId>mybatis-plus-boot-starter</artifactId>
    </dependency>
    ```   
1. provider模块添加组件扫描
    ```
    @SpringBootApplication(scanBasePackages = {"com.wyy.*", "com.easyray.*"})
    @EnableDubbo(scanBasePackages = "com.easyray.*")
    //必须指定只扫描Mapper.class，否者会扫描2次spring的component，导致重复而报错
    @MapperScan(annotationClass = Mapper.class, basePackages = {"com.easyray.*"})
    ```    
        
                
