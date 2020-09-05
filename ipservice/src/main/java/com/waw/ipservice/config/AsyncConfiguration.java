package com.waw.ipservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * @author flyfish
 * @date 2020-08-24 14:54:34
 * @description
 */
@Configuration
@EnableAsync
public class AsyncConfiguration {
    //声明一个线程池
    @Bean("uncheckedIpPool")
    public Executor uncheckedIpPool() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(20);
        executor.setMaxPoolSize(50);
        executor.setQueueCapacity(500);
        executor.setKeepAliveSeconds(60);
        executor.setThreadNamePrefix("uncheckedIpPool-");
        executor.initialize();
        return executor;
    }

    @Bean("checkedIpPool")
    public Executor checkedIpPool() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(20);
        executor.setMaxPoolSize(50);
        executor.setQueueCapacity(500);
        executor.setKeepAliveSeconds(60);
        executor.setThreadNamePrefix("checkedIpPool-");
        executor.initialize();
        return executor;
    }

    @Bean("notUseIpPool")
    public Executor notUseIpPool() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5);
        executor.setMaxPoolSize(20);
        executor.setQueueCapacity(500);
        executor.setKeepAliveSeconds(60);
        executor.setThreadNamePrefix("notUseIpPool-");
        executor.initialize();
        return executor;
    }

//    @Bean("ipCheckPool")
//    public Executor ipCheckPool() {
//        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
//        executor.setCorePoolSize(2);
//        executor.setMaxPoolSize(5);
//        executor.setQueueCapacity(500);
//        executor.setKeepAliveSeconds(60);
//        executor.setThreadNamePrefix("ipCheckPool-");
//        executor.initialize();
//        return executor;
//    }
}
