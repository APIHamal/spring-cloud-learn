package com.lizhengpeng.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient //开启Eureka客户端服务(表示当前是一个Eureka客户端)
@Slf4j
public class ApiServer8000 {

    public static void main(String[] args) {
        log.info("SpringBoot项目启动");
        SpringApplication.run(ApiServer8000.class, args);
    }
}
