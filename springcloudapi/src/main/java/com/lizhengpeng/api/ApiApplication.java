package com.lizhengpeng.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApiApplication {

    private final static Logger logger = LoggerFactory.getLogger(ApiApplication.class);

    public static void main(String[] args) {
        logger.info("SpringBoot项目启动");
        SpringApplication.run(ApiApplication.class, args);
    }
}
