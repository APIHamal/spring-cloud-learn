package com.lizhengpeng.learn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.netflix.ribbon.RibbonClients;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RibbonClients
public class CommonController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @GetMapping("/select")
    private String selectServer(){
        ServiceInstance serviceInstance = loadBalancerClient.choose("eureka-server");
        return serviceInstance.getHost()+"--->"+serviceInstance.getPort();
    }

    @GetMapping("/hello")
    public String sayHello(){
        System.out.println("进入了请求方法");
        return restTemplate.getForObject("http://eureka-server/hello",String.class);
    }

}
