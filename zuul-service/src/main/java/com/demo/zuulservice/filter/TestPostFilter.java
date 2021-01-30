package com.demo.zuulservice.filter;

import com.netflix.zuul.exception.ZuulException;
import org.springframework.cloud.netflix.zuul.filters.RouteLocator;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.web.util.UrlPathHelper;

/**
 * @author sjq
 */
//@Component
public class TestPostFilter extends MyAbstractFilter {

    TestPostFilter(RouteLocator routeLocator, UrlPathHelper urlPathHelper) {
        super(routeLocator, urlPathHelper);
    }

    @Override
    public String filterType() {
        return  FilterConstants.POST_TYPE;
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
        System.out.println("---------------------------post-----------------------------");
     //   System.out.println("---------------------------postResult-----------------------------"+getResult());
        System.out.println("---------------------------postRout-----------------------------"+getRoute());
        return null;
    }


}
