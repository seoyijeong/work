package com.example.demo.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Slf4j
@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    //HTTP 요청  →  WAS  →  필터  →  디스패처서블릿  →  스프링 인터셉터  →  컨트롤러
    //스프링 인터셉터는 서블릿 필터와는 URL 패턴을 보다 매우 정밀하게 설정할 수 있다.


    /*    @Override
        public void addInterceptors(InterceptorRegistry registry) {
           *//* registry.addInterceptor(authInterceptor)
                .addPathPatterns("/**")
                //addPathPatterns은 interceptor를 적용할 pattern에 대해 적는 공간
                .excludePathPatterns("/member/**", "/board/**");   //비 로그인 접근가능하도록
    }*/
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        log.info("addInterceptors>>>>>>>>>>>>>>>>");
        registry.addInterceptor(new AuthInterceptor())
                .addPathPatterns("/memberList/getMemberList");

       /* if (registration != null) {
            log.info("인터셉터 등록 성공");
        } else {
            log.warn("인터셉터 등록 실패");
        }*/
        //registry.addInterceptor(authInterceptor)
        //.excludePathPatterns("/board/**","/member/**");  //제외할패턴
        log.info("인터셉터>>>>>>>>>>>>>>>>");
    }
    @Bean
    public AuthInterceptor customInterceptor() {
        return new AuthInterceptor();
    }
}
