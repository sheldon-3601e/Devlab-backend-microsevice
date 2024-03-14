package com.sheldon.devlabbackendjudgeservice;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 主类（项目启动入口）
 *
 * @author <a href="https://github.com/sheldon-3601e">sheldon</a>
 * @from <a href="https://github.com/sheldon-3601e">sheldon</a>
 */
// todo 如需开启 Redis，须移除 exclude 中的内容
@SpringBootApplication
@EnableScheduling
@EnableAspectJAutoProxy(proxyTargetClass = true, exposeProxy = true)
@ComponentScan("com.sheldon")
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.sheldon.devlabbackendserviceclient.service")
public class DevlabBackendJudgeServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(DevlabBackendJudgeServiceApplication.class, args);
    }

}
