package com.lizhengpeng.learn.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="CONTROLLER-SERVER")
public interface APIServiceFeign {

    @RequestMapping(value = "/sayHello",method = RequestMethod.GET)
    String sayHello(@RequestParam(value = "name")String name);

    @RequestMapping(value = "/sayHelloException",method = RequestMethod.GET)
    String sayHelloException(@RequestParam("name") String name);

}
