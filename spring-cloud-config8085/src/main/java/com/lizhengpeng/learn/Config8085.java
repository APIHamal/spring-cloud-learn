package com.lizhengpeng.learn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @EnableConfigServer 注解用于启用一个Config服务
 * config服务默认使用git作为后端存储库如果要使用
 * 文件系统作为存储则要开启spring.profiles.active=native启动当前的Config服务
 * 否则ConfigServer无法正常启动
 *
 */
@EnableConfigServer
@SpringBootApplication
//@EnableEurekaClient
public class Config8085 {
    public static void main(String[] args) {
        SpringApplication.run(Config8085.class, args);
    }
}
