package com.lizhengpeng.learn.feign;

import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * Feign客户端调用失败时的回退机制
 * @author lzp
 */
@Component
public class SelfCallFeignFallbackFactory implements FallbackFactory<SelfCallFeign> {
    @Override
    public SelfCallFeign create(Throwable throwable) {
        System.out.println("接口调用失败");
        throwable.printStackTrace();
        return new SelfCallFeign() {
            @Override
            public String sayHelloException(String name) {
                return "远程接口调用失败";
            }
        };
    }
}
