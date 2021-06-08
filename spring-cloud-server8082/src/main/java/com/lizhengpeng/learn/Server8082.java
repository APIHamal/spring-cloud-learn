package com.lizhengpeng.learn;

import com.lizhengpeng.learn.feign.SelfCallFeign;
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
@RestController
@EnableCircuitBreaker
@EnableFeignClients
public class Server8082 {

    @Autowired
    private SelfCallFeign selfCallFeign;

    @GetMapping("/sayHello")
    public String sayHello(String name){
        if(name == null || name.trim().length() == 0){
            name = "empty text";
        }
        return "hello:"+name+",this is API-SERVER";
    }

    /**
     * 添加Hystrix支持进行服务熔断
     * @param name
     * @return
     */
    @HystrixCommand(fallbackMethod = "callFallBack")
    @GetMapping("/sayHelloException")
    public String sayHelloException(String name){
        if(name == null || name.trim().length() == 0){
            throw new IllegalArgumentException("name参数为空");
        }
        return "hello:"+name+",this is API-SERVER";
    }

    /**
     * 自身调用sayHelloException
     * @param name
     * @return
     */
    @GetMapping("/selfSayHello")
    public String selfSayHello(String name){
        return selfCallFeign.sayHelloException(name);
    }

    /**
     * Hystrix调用失败时候的回退方法
     * @param name
     */
    public String callFallBack(String name, Throwable e){
        if(e != null){
            e.printStackTrace();
        }
        return "Controller调用失败,Hystrix接管调用";
    }


    public static void main(String[] args) {
        SpringApplication.run(Server8082.class, args);
    }
}
