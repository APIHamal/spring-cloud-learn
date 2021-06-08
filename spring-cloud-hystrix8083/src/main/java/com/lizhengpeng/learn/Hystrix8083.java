package com.lizhengpeng.learn;

import com.lizhengpeng.learn.feign.APIServiceFeign;
import com.netflix.discovery.converters.Auto;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@RestController
@EnableCircuitBreaker //开启Hystrix熔断器的支持
public class Hystrix8083 {

    @Autowired
    private APIServiceFeign apiServiceFeign;

    @GetMapping("/sayHello")
    public String sayHello(String name){
        System.out.println("进入了sayHello方法");
        return apiServiceFeign.sayHello(name);
    }

    @GetMapping("/hystrix/sayHello")
    public String hystrixSayHello(String name){
        System.out.println("进入了hystrix/sayHello方法");
        return apiServiceFeign.sayHello(name);
    }

    @HystrixCommand(fallbackMethod = "fallBack")
    @GetMapping("/sayHelloException")
    public String sayHelloException(String name) {
        return apiServiceFeign.sayHelloException(name);
    }

    public String fallBack(String name){
        return "调用错误,Hystrix开始熔断服务";
    }

    public static void main(String[] args) {
        SpringApplication.run(Hystrix8083.class, args);
    }
}
