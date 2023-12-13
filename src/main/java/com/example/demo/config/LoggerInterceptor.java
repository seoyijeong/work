/*
package com.example.demo.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
public class LoggerInterceptor implements HandlerInterceptor {
    //preHandle : 특정 uri이 호출 됐을때 실행되는 메서드 컨트롤러를 접근하지 전에 실행되는 메서드
    // 사용자가 어떤 기능을 수행했는지 파악 하기 위해 해당 메서드(기능)와 매핑된 uri 정보가 로그가 출력되도록 처리
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.debug("begin");
        log.debug("request uri -->" + request.getRequestURI());
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    //postHandle : 컨트롤러를 접근 한 후, 화면으로 결과를 전달하기 전에 실행되는 메서드
    //preHandle()과는 반대로 요청(Request)의끝을 알리는 로그가 콘솔에 출력되도록 처리.
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
       log.debug("end");
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }
}
*/
