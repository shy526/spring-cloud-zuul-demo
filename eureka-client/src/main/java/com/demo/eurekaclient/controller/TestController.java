package com.demo.eurekaclient.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Administrator
 */
@RestController
@RequestMapping("test")
public class TestController {
    @GetMapping("get")
    Map<String,Object> get(Integer id){
        Map<String, Object> result = new HashMap<>(1);
        if (id!=null){
            result.put("id",id);
        }else {
            result.put("error","error");
        }

        return result;
    }
}
