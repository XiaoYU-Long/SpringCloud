package com.yu.servicefeign.feignclient;

import org.springframework.stereotype.Component;

/**
 * 服务提供熔断器
 *
 * @author yu
 * @date 2022-05-11 20:41
 */
@Component
public class ProviderServiceHystrixImpl implements ProviderService {
    @Override
    public String hello(String name) {
        return "hello," + name + ",sorry, the service is down!";
    }
}
