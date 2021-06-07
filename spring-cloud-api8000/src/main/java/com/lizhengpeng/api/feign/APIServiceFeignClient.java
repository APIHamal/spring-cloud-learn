package com.lizhengpeng.api.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "CONTROLLER-SERVER",fallbackFactory = APIServiceFeignClientFallback.class)
public interface APIServiceFeignClient {

    /**
     * 调用APIService的sayHello接口
     * @return
     */
    @RequestMapping(value = "/sayHello", method = RequestMethod.GET)
    String callSayHelloByFeign(@RequestParam("name") String name);

}
