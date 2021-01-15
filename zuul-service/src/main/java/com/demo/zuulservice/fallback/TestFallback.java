package com.demo.zuulservice.fallback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

/**
 * 熔断测试
 *
 * @author Administrator
 */
@Component
public class TestFallback implements FallbackProvider {
    private final static Logger LOGGER = LoggerFactory.getLogger(TestFallback.class);
    private final String   ERROR_FORMAT= "{'error':%s}";

    /**
     * 返回熔断的路由
     * @return String
     */
    @Override
    public String getRoute() {
        return "t1";
    }

    @Override
    public ClientHttpResponse fallbackResponse(String route, Throwable cause) {
        LOGGER.error("fallback----------------------------");
        String errMsg="";
        if (cause != null ) {
            errMsg=cause.getMessage();
        }

        return MyClientHttpResponse.getJsonResponse(String.format(ERROR_FORMAT,errMsg));
    }
}
