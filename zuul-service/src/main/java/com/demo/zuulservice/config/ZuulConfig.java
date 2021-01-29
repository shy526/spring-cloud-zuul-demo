package com.demo.zuulservice.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

/**
 * zuul 动态配置
 * @author sjq
 */
@Configurable
public class ZuulConfig {

    @Bean
    @RefreshScope
    @ConfigurationProperties("zuul")
    @Primary
    public ZuulProperties zuulProperties() {
        return new ZuulProperties();
    }
}
