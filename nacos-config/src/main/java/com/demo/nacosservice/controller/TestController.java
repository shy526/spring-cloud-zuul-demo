package com.demo.nacosservice.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Administrator
 */
@RestController
@RequestMapping("/config")
@RefreshScope
public class TestController {
    @Value("${useLocalCache:测试}")
    private String testStr;

    @RequestMapping("/get")
    public String get() {
        System.out.println("test = " + testStr);
        return testStr;
    }

}
