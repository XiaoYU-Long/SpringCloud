package com.lhy.serviceprovider.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 服务提供者控制层
 *
 * @author yu
 * @date 2022-05-09 9:55
 */
@RestController
@RequestMapping("/")
public class ProviderController {

    @Value("${server.port}")
    String port;

    @RequestMapping("/hello")
    public String hello(@RequestParam(value = "name", defaultValue = "dragon") String name) {
        return "hello " + name + ", I`m from port:" + port;
    }
}
