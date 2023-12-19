package com.example.demo.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*Springdoc은 Swagger UI 설정을 하는 방법이 Springfox와 다소 차이가 있다.
Springfox는 별도의 config 클래스에서 대부분의 설정을 하였지만
Springdoc는 config 클래스에서 API 문서페이지의 기본 설명만 작성하고
나머지 설정은 모두 application.properties 혹은 application.yml에서 설정한다.
또한 config 클래스에 @EnableWebMvc 어노테이션을 붙이지도 않는다.*/



@Configuration

public class SwaggerConfig {
    @Bean
    public GroupedOpenApi publicApi(){
        //pathsToMatch로 원하는 경로의  api만 나오도록 설정
        //함수 설정으로 초기화면 설정
        return GroupedOpenApi.builder()
                .group("board")
                .pathsToMatch("/**")
                .build();
    }
    @Bean
    public OpenAPI springShopOpenAPI(){
        String title = "Swagger Page Title";
        String description = "Swagger Page Description";

        Info info= new Info().title(title).description(description).version("1.0.0");
        return new OpenAPI().info(info);
    }

/*    @Bean
    public OpenAPI openAPI(){
        //String [] paths = {"/example/**"};
        return new OpenAPI()
                .components(new Components())
                //.paths(paths)
                .info(apiInfo());
    }
    private Info apiInfo(){
        return new Info()
                .title("SpringDoc test")
                .description("SpringDoc을 사용한 Swagger UI test")
                .version("1.0.0");
    }*/
}
