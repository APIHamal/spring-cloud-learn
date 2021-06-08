package com.lizhengpeng.learn.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 调用自身的Feign对象
 * @author lzp
 */
@FeignClient(name = "SERVER-PROVIDE", fallbackFactory = SelfCallFeignFallbackFactory.class)
public interface SelfCallFeign {

    /**
     * 调用当前模块自身的sayHelloException接口
     * @param name
     * @return
     */
    @RequestMapping(value = "/sayHelloException", method = RequestMethod.GET)
    String sayHelloException(@RequestParam("name") String name);

}
