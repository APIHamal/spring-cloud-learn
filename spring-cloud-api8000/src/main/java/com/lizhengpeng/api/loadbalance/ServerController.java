package com.lizhengpeng.api.loadbalance;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试通过Eureka进行HTTP服务的调用
 * @author lzp
 */
@RestController
public class ServerController {

    @GetMapping("/sayHello")
    public String sayHello(String name){
        if(name.length() <= 100){
            throw new RuntimeException("字符串数据为空");
        }
        return "hello:"+name+",this is API-SERVER";
    }

}
