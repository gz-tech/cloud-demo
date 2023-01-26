package com.upuphone.cloudplatform.demo.web;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author zhujiajun
 * @version 1.0
 * @since 2021/12/17 16:26
 */
@EnableApolloConfig
@EnableDiscoveryClient
@EnableFeignClients(basePackages = {"com.upuphone.cloudplatform.demo.api"})
@SpringBootApplication(scanBasePackages = "com.upuphone.cloudplatform")
public class DemoWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoWebApplication.class, args);
    }
}
