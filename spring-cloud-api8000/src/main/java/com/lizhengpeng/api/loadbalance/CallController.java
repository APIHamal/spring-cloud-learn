package com.lizhengpeng.api.loadbalance;

import com.lizhengpeng.api.feign.APIServiceFeignClient;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CallController {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private APIServiceFeignClient apiServiceFeignClient;

    @GetMapping("/callSayHello")
    public String callSayHello(){
        return restTemplate.getForObject("http://API-SERVER/sayHello",String.class);
    }

    @GetMapping("/callSayHelloByFeign")
    public String callSayHelloByFeign(String name){
        return apiServiceFeignClient.callSayHelloByFeign(name);
    }

}
