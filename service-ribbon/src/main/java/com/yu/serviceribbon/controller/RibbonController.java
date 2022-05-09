package com.yu.serviceribbon.controller;

import com.yu.serviceribbon.service.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * ribbon控制层
 *
 * @author yu
 * @date 2022-05-09 10:09
 */
@RestController
public class RibbonController {

    @Autowired
    private ProviderService providerService;

    @GetMapping("/hello")
    public String hello(@RequestParam String name) {
        return providerService.hello(name);
    }
}
