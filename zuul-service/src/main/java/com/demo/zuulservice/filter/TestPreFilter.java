package com.demo.zuulservice.filter;

import com.netflix.zuul.exception.ZuulException;
import org.springframework.cloud.netflix.zuul.filters.RouteLocator;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UrlPathHelper;

/**
 * pre 类型的设置
 *
 * @author Administrator
 */
//@Component
public class TestPreFilter extends MyAbstractFilter {

    TestPreFilter(RouteLocator routeLocator, UrlPathHelper urlPathHelper) {
        super(routeLocator, urlPathHelper);
    }

    @Override
    public String filterType() {
        return  FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        System.out.println("---------------------------pre-----------------------------");
        return null;
    }
}
