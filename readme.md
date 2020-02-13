#### 模块说明
1. auth  
    认证模块，可以账号密码登录，后期扩展微信登录，短信登录等
1. base-api  
    框架基础模块，包含了框架的公共api
1. codegen  
    代码生成模块
1. common   
    公共模块，提供公共api，与框架体系无关
1. idgenerator
    id生成器
1. resource-permission
    权限定义与检查
1. scripts
    启动服务脚本
#### 框架使用介绍
框架设计为4层结构，其中api层提供基础接口（interface）；provider层提供基础service（dubbo），操作数据库，权限检查；web层包含2层：业务层(consumer)和controller层，service层做复杂业务逻辑操作，controller对外提供接口（rest service）。
#### 注意事项
1. 所有的依赖关系全部从根pom文件查找，禁止模块引入特殊依赖
#### 开发步骤
1. 创建api模块（maven）
1. 创建provider模块（springboot）
1. api模块添加依赖
    ```
    <dependency>
        <groupId>com.wyy</groupId>
        <artifactId>base-api</artifactId>
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
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <scope>runtime</scope>
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
        
                
