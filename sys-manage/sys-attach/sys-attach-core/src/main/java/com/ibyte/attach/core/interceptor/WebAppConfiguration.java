package com.ibyte.attach.core.interceptor;

import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author <a href="mailto:shangzhi.ibyte@gmail.com">iByte</a>
 * @since 1.0.1
 */
@Configuration
public class WebAppConfiguration implements WebMvcConfigurer {

    @Autowired
    private RedissonClient redissonClient;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
    }
}