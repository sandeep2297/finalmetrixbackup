/**
 * Awards Microservice Swagger Config Class
 */

package com.metrix.awardsmicroservice.wwwservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    /**
     *
     *
     * Docket Function to specify the controller package, passing the metadata and specify url regex
     */
    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.metrix.awardsmicroservice.wwwservice.controller"))
                .paths(regex("/.*"))
                .build().apiInfo(metaData());
    }

    /**
     *
     *
     * Method to specify API Information metadata
     */
    private ApiInfo metaData() {
        ApiInfo apiInfo = new ApiInfo(
                "Awards Microservice Spring Boot REST API",
                "Spring Boot REST API for Awards Microservice in Metrix Platform",
                "1.0",
                "Terms of Service",
                new Contact("Metrix Team", "https://www.metrix.com/about/", "team@metrix.com"),
                "MIT License",
                "https://opensource.org/licenses/MIT");
        return apiInfo;
    }
}
