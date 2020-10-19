package com.xiatiao.httpbin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class HttpbinApplication {

    public static void main(String[] args) {
        SpringApplication.run(HttpbinApplication.class, args);
    }


    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .useDefaultResponseMessages(false)
                .tags(
                        new Tag("HTTP Methods", "Testing different HTTP verbs"),
                        new Tag("Request Inspection", "Inspect the request data")
                )
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.xiatiao.httpbin"))
                .paths(PathSelectors.regex("/.*"))
                .build()
                .pathMapping("/");
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Httpbin")
                .description("A simple HTTP Request & Response Service.\n" + "<a href=\"https://github.com/wych42/httpbin\">Source Code</a>")
                .version("2.0")
                .build();
    }
}
