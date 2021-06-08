package com.lizhengpeng.learn.feign;

import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class APIServiceFeignFallbackFactory implements FallbackFactory<APIServiceFeign> {
    @Override
    public APIServiceFeign create(Throwable throwable) {
        System.out.println("调用出现异常");
        throwable.printStackTrace();
        return new APIServiceFeign(){

            @Override
            public String sayHello(String name) {
                return "-1";
            }

            @Override
            public String sayHelloException(String name) {
                return "-1";
            }
        };
    }
}
