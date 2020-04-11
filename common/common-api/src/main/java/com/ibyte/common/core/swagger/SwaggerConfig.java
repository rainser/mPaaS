package com.ibyte.common.core.swagger;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.ibyte.common.constant.NamingConstant;
import com.ibyte.framework.discovery.ModuleMappingHelper;
import com.ibyte.framework.discovery.dto.ModuleMappingInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Swagger 配置
 *
 * 前端通过/swagger-ui.html访问得到地址
 *
 * @author <a href="mailto:shangzhi.ibyte@gmail.com">iByte</a>
 * @since 1.0.1
 */
@Configuration
@EnableSwagger2
@ConditionalOnProperty(value = "mpaas.auth.swaggerEnable", havingValue = "true")
public class SwaggerConfig {

    @Resource
    private ModuleMappingHelper mappingHelper;

    @Resource
    private ApplicationContext applicationContext;

    /**
     * 动态产生Docket分组信息
     *
     * @return
     */
    @Autowired
    public void dynamicConfiguration() {
        ConfigurableApplicationContext context = (ConfigurableApplicationContext) applicationContext;
        DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory) context.getBeanFactory();

        String systemName = "Microservice PaaS";
        ApiInfoBuilder apiInfoBuilder = new ApiInfoBuilder()
                .description("mPaas前端后端对应接口")
                .version("1.0.1")
                .license("Code Farmer Framework(iByte) Org.");

        Map<String, ModuleMappingInfo> moduleInfo = mappingHelper.getMappingInfos();
        for (Map.Entry<String, ModuleMappingInfo> entry : moduleInfo.entrySet()) {
            beanFactory.registerSingleton(entry.getKey(), new Docket(DocumentationType.SWAGGER_2)
                    .groupName(entry.getKey())
                    .apiInfo(apiInfoBuilder.title(systemName + NamingConstant.DOT + entry.getKey()).build())
                    .select()
                    .apis(genSubPackage(entry.getKey()))
                    .paths(Predicates.or(PathSelectors.ant(NamingConstant.PATH_PREFIX_DATA + "/**"),
                            PathSelectors.ant(NamingConstant.PATH_PREFIX_API + "/**")))
                    .build());
        }
    }

    /**
     * 模块路径
     *
     * @param moduleName
     * @return
     */
    private Predicate<RequestHandler> genSubPackage(String moduleName) {
        return RequestHandlerSelectors.basePackage(NamingConstant.BASE_PACKAGE
                + NamingConstant.DOT
                + moduleName.replace(NamingConstant.STRIKE, NamingConstant.DOT));
    }

}
