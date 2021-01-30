package com.demo.zuulservice.filter;

import com.google.common.util.concurrent.RateLimiter;
import com.netflix.zuul.context.RequestContext;
import org.springframework.cloud.netflix.zuul.filters.RouteLocator;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UrlPathHelper;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 令牌桶限流
 *
 * @author sjq
 */
@SuppressWarnings("UnstableApiUsage")
@Component
public class RateLimitFilter extends MyAbstractFilter {

    private static final RateLimiter RATE_LIMITER = RateLimiter.create(15.0D);
    private static final SimpleDateFormat YYYY_MM_DD=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    RateLimitFilter(RouteLocator routeLocator, UrlPathHelper urlPathHelper) {
        super(routeLocator, urlPathHelper);
    }

    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext context = RequestContext.getCurrentContext();
         String id ="threadId="+Thread.currentThread().getId()+"-->"+System.currentTimeMillis()+"-->";
        if (!RATE_LIMITER.tryAcquire()) {
            context.setSendZuulResponse(false);
            context.setResponseStatusCode(200);
            HttpServletResponse response = context.getResponse();
            response.addHeader("Content-Type","text/plain;charset=UTF-8");
            context.setResponseBody("稍后");
            System.out.println(id+"pre限流限制");
            return false;
        }
        System.out.println(id+"pre限流通过");
        return true;
    }

    @Override
    public Object run() {
        return null;
    }
}
