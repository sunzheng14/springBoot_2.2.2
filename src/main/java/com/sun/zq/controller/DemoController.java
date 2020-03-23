package com.sun.zq.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class DemoController {

    @Resource(name="demo")
    private DemoService demoService;

    @RequestMapping("/say")
    public String sayWhat() {
        return demoService.say();
    }

}
