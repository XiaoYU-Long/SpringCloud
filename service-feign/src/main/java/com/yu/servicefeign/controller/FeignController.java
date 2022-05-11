package com.yu.servicefeign.controller;

import com.yu.servicefeign.feignclient.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FeignController {

    @Autowired
    private ProviderService providerService;

    @GetMapping("/hello")
    public String hello(@RequestParam String name) {
        return providerService.hello(name);
    }
}
