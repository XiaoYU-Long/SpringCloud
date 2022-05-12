package com.yu.configclient.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 客户端控制层
 *
 * @author yu
 * @date 2022-05-12 20:34
 */
@RestController
@RefreshScope
public class ClientController {

    @Value("${environment}")
    private String environment;

    @RequestMapping("/hello")
    public String hello() {
        return environment;
    }
}
