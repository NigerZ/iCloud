package com.ohh.icloud.config;

import com.ohh.icloud.handler.TokenLoginIntercepter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Configuration
public class MyWebConfiguration extends WebMvcConfigurerAdapter {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new TokenLoginIntercepter()).addPathPatterns("/**");
        super.addInterceptors(registry);
    }



    @Value("${icloud.threadPool.corePoolSize}")
    private int corePoolSize;
    @Value("${icloud.threadPool.maximumPoolSize}")
    private int maximumPoolSize;
    @Value("${icloud.threadPool.keepAliveTime}")
    private Long keepAliveTime;

    /**全局线程池*/
    @Bean
    public ThreadPoolExecutor executorService() {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize,
                keepAliveTime, TimeUnit.SECONDS,
                new ArrayBlockingQueue(10));
        return threadPoolExecutor;
    }
}
