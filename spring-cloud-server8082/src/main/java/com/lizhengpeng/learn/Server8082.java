package com.lizhengpeng.learn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableEurekaClient
@RestController
public class Server8082 {

    @GetMapping("/sayHello")
    public String sayHello(String name){
        if(name.length() <= 100){
            throw new RuntimeException("字符串数据长度小于100");
        }
        return "hello:"+name+",this is API-SERVER";
    }

    public static void main(String[] args) {
        SpringApplication.run(Server8082.class, args);
    }
}
