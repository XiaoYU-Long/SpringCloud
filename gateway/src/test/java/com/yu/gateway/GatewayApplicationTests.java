package com.yu.gateway;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

@SpringBootTest
@RunWith(SpringRunner.class)
public class GatewayApplicationTests {

    @Test
    public void zonedDateTime() {
        LocalDateTime now = LocalDateTime.now();
        System.out.println(now);
        System.out.println("ZoneId.systemDefault(): " + ZoneId.systemDefault());

        // convert LocalDateTime to ZonedDateTime, with default system zone id
        ZonedDateTime zonedDateTime = now.atZone(ZoneId.systemDefault());

        // convert LocalDateTime to ZonedDateTime, with specified zoneId
        ZonedDateTime asiaDateTime = zonedDateTime.withZoneSameInstant(ZoneId.of("Asia/Shanghai"));
        System.out.println(asiaDateTime);

        // convert LocalDateTime to ZonedDateTime, with specified off set
        ZonedDateTime offSetNegative5 = now.atOffset(ZoneOffset.of("-05:00")).toZonedDateTime();
        System.out.println(offSetNegative5);

    }

}
