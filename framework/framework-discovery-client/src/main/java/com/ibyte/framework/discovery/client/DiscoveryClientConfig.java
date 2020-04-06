package com.ibyte.framework.discovery.client;
import com.ibyte.common.constant.NamingConstant;
import com.ibyte.framework.discovery.client.feign.FeignErrorDecoder;
import com.ibyte.framework.discovery.client.interceptor.ApiFeignInterceptor;
import com.netflix.appinfo.ApplicationInfoManager;
import feign.RequestInterceptor;
import feign.codec.ErrorDecoder;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 服务注册客户端配置类
 *
 * @author <a href="mailto:shangzhi.ibyte@gmail.com">iByte</a>
 * @since 1.0.1
 */
@Configuration
@EnableDiscoveryClient
@EnableFeignClients(basePackages = NamingConstant.BASE_PACKAGE)
@EnableCircuitBreaker
public class DiscoveryClientConfig implements ApplicationContextAware {

    @Autowired
    ApplicationInfoManager applicationInfoManager;

    @Autowired
    RequestMappingHandlerMapping requestMappingHandlerMapping;

    /**
     * feign请求拦截器
     *
     * @return
     */
    @Bean
    public RequestInterceptor feignInterceptor() {
        return new ApiFeignInterceptor();
    }

    /**
     * feign 异常处理
     * @return
     */
    @Bean
    public ErrorDecoder feignErrorDecoder(){
        return new FeignErrorDecoder();
    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Map<String, String> metaData = new HashMap<>();
        List<String> urls = new ArrayList<>();
        requestMappingHandlerMapping.getHandlerMethods().forEach((requestMappingInfo, handlerMethod) -> {
            urls.addAll(requestMappingInfo.getPatternsCondition().getPatterns());
        });
        metaData.put("mappings", String.join(",", urls));
        applicationInfoManager.registerAppMetadata(metaData);
    }
}
