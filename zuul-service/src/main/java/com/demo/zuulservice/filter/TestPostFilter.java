package com.demo.zuulservice.filter;

import com.netflix.zuul.exception.ZuulException;
import org.springframework.cloud.netflix.zuul.filters.RouteLocator;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UrlPathHelper;

/**
 * @author sjq
 */
@Component
public class TestPostFilter extends MyAbstractFilter {

    TestPostFilter(RouteLocator routeLocator, UrlPathHelper urlPathHelper) {
        super(routeLocator, urlPathHelper);
    }

    @Override
    public String filterType() {
        return FilterTypeEnum.POST.getTypeString();
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
        System.out.println(getResult());
        System.out.println(getRoute());
        return null;
    }


}
