package com.yu.serviceribbon.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * 服务提供者服务层
 *
 * @author yu
 * @date 2022-05-09 10:05
 */
@Service
public class ProviderService {

    @Autowired
    private RestTemplate restTemplate;

    public String hello(String name) {
        return restTemplate.getForObject("http://SERVICE-PROVIDER/hello?name=" + name, String.class);
    }
}
