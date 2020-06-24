package com.easyray.teamweb;

import org.apache.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.easyray.*"})
@DubboComponentScan(basePackages = {"com.easyray.*"})
public class TeamWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(TeamWebApplication.class, args);
    }

}
