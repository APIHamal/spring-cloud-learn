package com.lizhengpeng.learn.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

/**
 * Zuul的回退机制(Zuul默认整合了hystrix模块)
 * @author lzp
 */
@Component
public class ZuulFallback implements FallbackProvider {

    private static final Logger logger = LoggerFactory.getLogger(ZuulFallback.class);

    /**
     * 该方法表示对哪些微服务起作用'*'表示对所有微服务转发都生效
     * 如果让其对指定的微服务生效则配置微服务注册到eureka中的服务名
     * 即可(通常就是spring.application.name)
     * @return
     */
    @Override
    public String getRoute() {
        return "*";
    }

    @Override
    public ClientHttpResponse fallbackResponse(String route, Throwable cause) {
        logger.info("Zuul转发发生了异常", cause);
        return new ClientHttpResponse() {

            /**
             * 返回一个HTTP的状态码
             * @return
             * @throws IOException
             */
            @Override
            public HttpStatus getStatusCode() throws IOException {
                return HttpStatus.BAD_GATEWAY;
            }

            /**
             * 该属性同HttpStatus相同即可
             * @return
             * @throws IOException
             */
            @Override
            public int getRawStatusCode() throws IOException {
                return HttpStatus.BAD_GATEWAY.value();
            }

            /**
             * 该属性同HttpStatus相同即可
             * @return
             * @throws IOException
             */
            @Override
            public String getStatusText() throws IOException {
                return HttpStatus.BAD_GATEWAY.name();
            }

            @Override
            public void close() {
                //空方法即可
            }

            /**
             * 返回HTTP中的响应体
             * @return
             * @throws IOException
             */
            @Override
            public InputStream getBody() throws IOException {
                String text = "{status:'error',message:'zuul转发出现异常'}";
                return new ByteArrayInputStream(text.getBytes(StandardCharsets.UTF_8));
            }

            /**
             * 设置响应信息为JSON
             * @return
             */
            @Override
            public HttpHeaders getHeaders() {
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);
                return headers;
            }
        };
    }
}
