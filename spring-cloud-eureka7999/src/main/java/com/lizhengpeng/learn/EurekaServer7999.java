package com.lizhengpeng.learn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableEurekaServer
@RestController
public class EurekaServer7999 {

    @GetMapping("/hello")
    public String sayHello(){
        System.out.println("EurekaServer接收到请求");
        return "hello client";
    }

    public static void main(String[] args) {
        SpringApplication.run(EurekaServer7999.class, args);
    }
}
