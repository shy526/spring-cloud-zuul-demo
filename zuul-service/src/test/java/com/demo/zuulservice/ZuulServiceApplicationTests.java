package com.demo.zuulservice;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;

@SpringBootTest
class ZuulServiceApplicationTests {

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    @Test
    void contextLoads() {
    }

    @Test
    void rateLimitFilterTest() {
        int total = 100;
        RestTemplate restTemplate = restTemplateBuilder.build();
        CyclicBarrier cyclicBarrier = new CyclicBarrier(total);
        CountDownLatch countDownLatch = new CountDownLatch(total);
        AtomicInteger s = new AtomicInteger(0);
        AtomicInteger f = new AtomicInteger(0);
        //每(1/?*1000)毫秒秒方放一个令牌
        for (int i = 1; i <= total; i++) {
            new Thread(() -> {
                try {
                    cyclicBarrier.await();
                    ResponseEntity<String> forEntity = restTemplate.getForEntity("http://localhost:8762/api2/test/get?test=xxxxx", String.class);
                    String body = forEntity.getBody();
                    if ("稍后".equals(body)) {
                        f.getAndIncrement();
                    } else {
                        s.getAndIncrement();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    countDownLatch.countDown();
                }
            }).start();
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("成功" + s.get());
        System.out.println("失败" + f.get());
    }
}
