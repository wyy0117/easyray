package com.easyray.login;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.easyray.*"})
@EnableDubbo
public class EasyrayFrameworkCoreLoginApplication {

    public static void main(String[] args) {
        SpringApplication.run(EasyrayFrameworkCoreLoginApplication.class, args);
    }

}
