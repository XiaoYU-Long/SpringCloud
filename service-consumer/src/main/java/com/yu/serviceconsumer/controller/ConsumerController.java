package com.yu.serviceconsumer.controller;

import brave.sampler.Sampler;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * 消费者控制层
 *
 * @author yu
 * @date 2022-05-15 18:17
 */
@RestController
@RequestMapping("/")
public class ConsumerController {

    private static final Logger logger = LoggerFactory.getLogger(ConsumerController.class);

    @Value("${server.port}")
    String port;

    @Autowired
    private RestTemplate restTemplate;

    @Bean
    public Sampler defaultSampler() {
        return Sampler.ALWAYS_SAMPLE;
    }

    @RequestMapping("/call")
    public String call() {
        logger.info("calling trace service-consumer");
        return restTemplate.getForObject("http://SERVICE-PROVIDER/info", String.class);
    }

    @RequestMapping("/info")
    public String info() {
        logger.info("calling trace service-consumer");
        return "I`m service-consumer";
    }

    @RequestMapping("/hello")
    @HystrixCommand(fallbackMethod = "helloError")
    public String hello(@RequestParam(value = "name", defaultValue = "dragon") String name) {
        return "hello " + name + ", I`m from port:" + port;
    }

    public String helloError(String name) {
        return "hi," + name + ", sorry, error happens";
    }
}
