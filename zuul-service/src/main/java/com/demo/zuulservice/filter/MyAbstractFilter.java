package com.demo.zuulservice.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import io.micrometer.core.instrument.util.IOUtils;
import org.springframework.cloud.netflix.zuul.filters.Route;
import org.springframework.cloud.netflix.zuul.filters.RouteLocator;
import org.springframework.web.util.UrlPathHelper;

import java.io.InputStream;

/**
 * @author Administrator
 */
public abstract class MyAbstractFilter extends ZuulFilter {
    private final RouteLocator routeLocator;
    private final UrlPathHelper urlPathHelper;

    MyAbstractFilter(RouteLocator routeLocator, UrlPathHelper urlPathHelper) {
        this.routeLocator = routeLocator;
        this.urlPathHelper = urlPathHelper;
    }

    /**
     * 获取路由信息
     * @return Route
     */
    protected Route getRoute() {
        RequestContext requestContext = RequestContext.getCurrentContext();
        return routeLocator.getMatchingRoute(urlPathHelper.getPathWithinApplication(requestContext.getRequest()));
    }

    /**
     * 获取ResponseBody 并重新设置
     * @return String
     */
    protected String getResult() {
        RequestContext requestContext = RequestContext.getCurrentContext();
        InputStream stream = requestContext.getResponseDataStream();
        String result = "";
        if (stream != null) {
            result = IOUtils.toString(stream);
            RequestContext.getCurrentContext().setResponseBody(result);
        }
        return result;
    }
}
