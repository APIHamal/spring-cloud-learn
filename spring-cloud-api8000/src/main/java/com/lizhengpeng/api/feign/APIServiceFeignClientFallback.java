package com.lizhengpeng.api.feign;

import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 使用FallbackFactory回退feign的请求时必须要开启feign.hystrix.enable=true
 * 否则即使配置了FallbackFactory也不会造成回退生效
 */
@Component
@Slf4j
public class APIServiceFeignClientFallback implements FallbackFactory<APIServiceFeignClient> {

    @Override
    public APIServiceFeignClient create(Throwable throwable){
        log.info("调用远程[create]接口服务失败", throwable);
        return new APIServiceFeignClient(){
            @Override
            public String callSayHelloByFeign(String name) {
                return "-1";
            }
        };
    }
}
