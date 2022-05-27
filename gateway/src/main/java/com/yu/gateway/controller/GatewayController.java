package com.yu.gateway.controller;

import com.yu.gateway.filter.RequestTimeFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * 路由转发
 *
 * @author yu
 * @date 2022-05-19 16:56
 */
@RestController
public class GatewayController {

    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder) {
        String uri = "http://httpbin.org:80";
        return builder.routes()
                .route(p -> p.path("/get")
                        .filters(f -> f.addRequestHeader("Hello", "World"))
                        .uri(uri))
                // cmd测试命令： curl -H "Host: www.hystrix.com" http://localhost:8080/delay/3
                .route(p -> p.host("*.hystrix.com")
                        .filters(f -> f.hystrix(config -> config
                                .setName("cmd")
                                .setFallbackUri("forward:/fallback")))
                        .uri(uri))
                // 自定义过滤器
                .route(p -> p.path("/customer/**")
                        .filters(f -> f.filter(new RequestTimeFilter()))
                        .uri(uri + "/get")
                        .order(0)
                        .id("customer_filter_router"))
                .build();
    }

    @RequestMapping("/fallback")
    public Mono<String> fallback() {
        return Mono.just("fallback");
    }

}
