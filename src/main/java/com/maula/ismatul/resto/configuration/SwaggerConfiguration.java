package com.maula.ismatul.resto.configuration;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Value;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.service.Contact;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
public class SwaggerConfiguration {

    @Value("${swagger.host}")
    private String swaggerHost;
    
    private Docket getBaseDocket(){
        return new Docket(DocumentationType.SWAGGER_2)
        		.host(swaggerHost)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.maula.ismatul.resto.controller"))
                .build()
                .apiInfo(getDefaultApiInfo())
                .useDefaultResponseMessages(false);
    }

    private ApiInfo getDefaultApiInfo() {
        return new ApiInfo("Resto Docs", "API sandbox for Resto service. Only for development purpose and API discovery.", "v1.11", "http://swagger.io/terms/", 
    		new Contact ("Ismatul Maula", "", "ismatulmaula67@gmail.com"), 
    		"Apache 2.0", "http://www.apache.org/licenses/LICENSE-2.0", Collections.emptyList());
	}

    @Bean
    public Docket docket() {
        return getBaseDocket()
        .select()
        .build().groupName("All APIs")
        .select().build();
    }
    
}
