/*
package com.example.demo.Controller;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Tag(name = "예제 API", description = "Swagger 테스트용 API")
@RestController
@RequestMapping("/example")
public class ExampleController {

    @Operation(summary = "문자열반복",  description = "파라미터로 받은 문자열을 2번 반복합니다")
    @Parameter(name = "str", description = "2번 반복할 문자열")
    @GetMapping("/returnStr")
    public String returnStr (@RequestParam String str){
        log.info("dddddd>>>{}", str);
        return str +"\n" +str;
    }
    @GetMapping("/example")
    public String example(){
        return "예시 API";
    }
    @Hidden
    @GetMapping("/ignore")
    public String ignore(){
        return "무시되는 api";
    }
}
//localhost:8080/swagger-ui/index.html*/
