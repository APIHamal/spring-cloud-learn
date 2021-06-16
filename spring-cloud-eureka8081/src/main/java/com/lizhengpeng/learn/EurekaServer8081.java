package com.lizhengpeng.learn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableEurekaServer //开启Eureka服务端(允许客户端进行连接)
@RestController
public class EurekaServer8081 {

    /**
     * 通过DiscoverClient对象可以到Eureka查询具体的微服务的地址
     */
    @Autowired
    private DiscoveryClient discoveryClient;

    @GetMapping("/hello")
    public String sayHello(){
        System.out.println("EurekaServer接收到请求");
        return "hello client";
    }

    @GetMapping("/printMicroServiceByName")
    public List<String> printMicroServiceByName(String name){
        //获取指定的服务实例的信息
        List<String> serviceList = new ArrayList<>();
        if(name == null || name.trim().length() == 0){
            return serviceList;
        }
        List<ServiceInstance> instances = discoveryClient.getInstances(name);
        for(ServiceInstance instance : instances){
            serviceList.add(instance.getServiceId()+"    "+instance.getHost()+"   "+instance.getPort());
        }
        return serviceList;
    }

    public static void main(String[] args) {
        SpringApplication.run(EurekaServer8081.class, args);
    }
}
