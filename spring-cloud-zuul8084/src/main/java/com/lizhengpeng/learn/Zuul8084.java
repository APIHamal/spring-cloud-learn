package com.lizhengpeng.learn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @EnableZuulProxy ###该注解启用ZUUL的代理功能
 * ZUUL中路由相关配置
 * routes -> Map<String, ZuulProperties.ZuulRoute> routes ### routes本身是一个Map结构
 * ZuulRoute ### ZuulRoute是Zuul配置路由的关键对象
 * ZuulRoute -> private String id;
 *              private String path;        ### 配置匹配的路径模式(例如/user/**[*表示任意字符**表示任意目录?表示一个字符])
 *              private String serviceId;   ### Eureka中的服务ID
 *              private String url;         ### 配置具体的机器地址(后端的位置可以指定为serviceId（用于发现服务）或url（用于物理位置），如以下示例所示)
 *                                          zuul:
 *                                              routes:
 *                                                users:
 *                                                  path: /myusers/**
 *                                                  sensitiveHeaders: Cookie,Set-Cookie,Authorization ### 配置敏感HTTP头部Zuul转发时会删除该头部再转发到下游系统
 *                                                  url: https://example.com/users_service
 *                                          ### 该配置用于将myusers转发到example.com的机器上的指定的URL
 *              private boolean stripPrefix = true; ### 是否删除URL头部(/user-service/listUser/1配置该属性后转发到user服务时变成/listUser/1默认删除)
 *              private Boolean retryable;
 *              private Set<String> sensitiveHeaders = new LinkedHashSet(); ### 配置ZUUL忽略HTTP头部中的敏感字段配置后该项后ZUUL转发会删除该字段
 *              private boolean customSensitiveHeaders = false;
 *
 *
 */
@SpringBootApplication
@EnableEurekaClient
@EnableZuulProxy
public class Zuul8084 {
    public static void main(String[] args) {
        SpringApplication.run(Zuul8084.class, args);
    }
}
