package com.yu.serviceconsumer.controller;

import brave.sampler.Sampler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
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
}
