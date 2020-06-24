package com.easyray.login;

import org.apache.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.easyray.*"})
@DubboComponentScan(basePackages = "com.easyray.*")
public class EasyrayLoginApplication {

    public static void main(String[] args) {
        SpringApplication.run(EasyrayLoginApplication.class, args);
    }

}
