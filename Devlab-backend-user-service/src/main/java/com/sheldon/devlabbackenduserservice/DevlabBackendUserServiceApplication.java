package com.sheldon.devlabbackenduserservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan("com.sheldon.devlabbackenduserservice.mapper")
@EnableScheduling
@EnableAspectJAutoProxy(proxyTargetClass = true, exposeProxy = true)
@ComponentScan("com.sheldon")
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.sheldon.devlabbackendserviceclient.service")
public class DevlabBackendUserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DevlabBackendUserServiceApplication.class, args);
	}

}
