package com.lizhengpeng.learn.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * 配置ZUUL的过滤器
 * @author lzp
 */
@Component //必须要注入到Spring中否则不会起生效
public class CommonFilter extends ZuulFilter {

    private static final Logger logger = LoggerFactory.getLogger(CommonFilter.class);

    /**
     * 配置过滤器的类型
     * pre->表示在转发请求之前进行处理通常使用此类型完成鉴权等操作
     * route->表示构造完成了请求准备发送给目标地址(此阶段可以再次编辑构造请求)
     * post->表示已经接受到了服务器的响应(此阶段可以修改服务器的响应信息)
     * error->表示上述任意阶段发生异常时会调用
     *
     * @return
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * 配置拦截器的执行顺序通常顺序越小拦截器越先执行
     * @return
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * 表示该拦拦截器是否应该执行
     * true表示需要被执行
     * false表示不会被执行
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 业务逻辑的处理
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {
        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();
        logger.info("请求的URL "+request.getRequestURI());
        Cookie[] cookies = request.getCookies();
        if(cookies != null){
            for(Cookie cookie : cookies){
                logger.info("cookie信息 "+cookie.getName()+"  "+cookie.getValue());
            }
        }
        return "-1";
    }
}
