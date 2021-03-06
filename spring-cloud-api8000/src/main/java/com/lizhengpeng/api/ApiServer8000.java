package com.lizhengpeng.api;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

@SpringBootApplication
@EnableEurekaClient //开启Eureka客户端服务(表示当前是一个Eureka客户端)
@EnableFeignClients //开启客户端当前的Feign调用组件
//@EnableCircuitBreaker //Feign开启Hystrix支持时必须启用此注解(feign.hystrix.enable也必须开启否则FeignClient中fallBack不生效)
@RestController
@Slf4j
public class ApiServer8000 {

    @GetMapping("/sayHello")
    public String sayHello(HttpServletRequest request,String name){
        Enumeration<String> nameEnumeration = request.getHeaderNames();
        //测试输出Sleuth头部信息
        while(nameEnumeration.hasMoreElements()){
            String keyName = nameEnumeration.nextElement();
            log.info("输出头部信息:"+keyName+"--->"+request.getHeader(keyName));
        }
        return "hello:"+name;
    }

    public static void main(String[] args) {
        log.info("SpringBoot项目启动");
        SpringApplication.run(ApiServer8000.class, args);
    }
}
