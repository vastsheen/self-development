package com.xh.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    //配置swagger2核心配置 docket，主要是添加要扫描controller的路径
    @Bean
    public Docket createResApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select().apis(RequestHandlerSelectors
                        .basePackage("com.xh.demo.controller"))
                .paths(PathSelectors.any())
                .build();

    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("demoApi")
                .description("测试swagger项目")
                .version("1.0.0")
                .build();

    }


    @Configuration
    class WebConfig extends WebMvcConfigurationSupport {
        @Override
        protected void addResourceHandlers(ResourceHandlerRegistry registry) {
            registry.addResourceHandler("/swagger-ui/**")
                    .addResourceLocations("classpath:/META-INF/resources/webjars/springfox-swagger-ui/")
                    .resourceChain(false);
            super.addResourceHandlers(registry);
        }
    }
}