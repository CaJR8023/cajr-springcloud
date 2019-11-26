package com.cajr.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.spring.web.readers.operation.CachingOperationNameGenerator;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger.web.UiConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author CAJR
 * @date 2019/11/26 2:27 下午
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    final List<ResponseMessage> globalResponse = Arrays.asList(
            new ResponseMessageBuilder()
            .code(200)
            .message("成功")
            .responseModel(new ModelRef("success")).build(),
            new ResponseMessageBuilder()
                    .code(201)
                    .message("添加或者修改成功")
                    .build(),
            new ResponseMessageBuilder()
                    .code(400)
                    .message("错误请求")
                    .build(),
            new ResponseMessageBuilder()
                    .code(401)
                    .message("未授权")
                    .build(),
            new ResponseMessageBuilder()
                    .code(403)
                    .message("拒绝请求")
                    .build(),
            new ResponseMessageBuilder()
                    .code(404)
                    .message("未找到相关的请求")
                    .build(),
            new ResponseMessageBuilder()
                    .code(500)
                    .message("服务器内部错误")
                    .build()
    );

    @Bean
    public Docket createRestApi() {
        ParameterBuilder parameterBuilder = new ParameterBuilder();
        parameterBuilder.name("Authorization").description("认证token").modelRef(new ModelRef("string"))
                .parameterType("header").required(true).build();

        List<Parameter> addParameters = new ArrayList<>();
        addParameters.add(parameterBuilder.build());

        return new Docket(DocumentationType.SWAGGER_2).extensions(getExtension())
                .apiInfo(getApiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.cajr.controller"))
                .build()
                .useDefaultResponseMessages(false)
                .globalResponseMessage(RequestMethod.GET,globalResponse)
                .globalResponseMessage(RequestMethod.POST,globalResponse)
                .globalResponseMessage(RequestMethod.PUT,globalResponse)
                .globalResponseMessage(RequestMethod.DELETE,globalResponse)
                .globalOperationParameters(addParameters);
    }

    private ApiInfo getApiInfo() {
        Contact contact = new Contact("CAJR","https://github.com/CaJR8023","1206303099@qq.com");
        return new ApiInfoBuilder()
                .title("CAJR")
                .description("推荐系统api")
                .version("1.0.0")
                .contact(contact)
                .build();
    }

    /**
     * @Description 增加顶层扩展
     * @return List<VendorExtension></>
     */
    private List<VendorExtension> getExtension() {
        List<VendorExtension> vendorExtensions = new ArrayList<>();

        List<String> stringList = new ArrayList<>();
        stringList.add("http");

        VendorExtension vendorExtension = new ListVendorExtension<>("schemes",stringList);
        vendorExtensions.add(vendorExtension);

        return vendorExtensions;
    }

    @Autowired
    private CachingOperationNameGenerator cachingOperationNameGenerator;

    @Bean
    public UiConfiguration uiConfiguration(){
        return UiConfigurationBuilder.builder().validatorUrl("").build();
    }
}
