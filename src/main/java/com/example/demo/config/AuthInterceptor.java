package com.example.demo.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.UUID;

//인터셉터란 컨트롤러에 들어오는 요청 HttpRequest와 컨트롤러가 응답하는 HttpResponse를 가로채는 역할
// Spring Interceptor는 Handler의 역할을 하는 controller로 가기전에 가로채기 때문에 정식 명칭은 HandlerInterceptor이다.
//컨트롤러(즉 RequestMapping이 선언된 메서드 진입) 실행 직전에 동작합니다.

@Slf4j
//@Component
//기본적으로 타입기반의 자동주입 어노테이션이다.
//Bean Configuration 파일에 Bean을 따로 등록하지 않아도 사용할 수 있다.
//빈 등록자체를 빈 클래스 자체에다가 할 수 있다는 의미
public class AuthInterceptor implements HandlerInterceptor {
    //preHandle : 특정 uri이 호출 됐을때 실행되는 메서드 컨트롤러를 접근하지 전에 실행되는 메서드
    // 사용자가 어떤 기능을 수행했는지 파악 하기 위해 해당 메서드(기능)와 매핑된 uri 정보가 로그가 출력되도록 처리

    //public static final String LOG_ID = "logId";

    //전달인자 중 Object handler는 핸들러 매핑이 찾은 컨트롤러 클래스 객체
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

/*        String requestURI = request.getRequestURI();
        String uuid = UUID.randomUUID().toString();  // - 요청 로그를 구분하기 위한 uuid 생성

        // LogInterceptor는 싱글톤으로 관리되기 때문에 uuid를 afterCompletion으로 넘길 때 멤버변수로 넘기면 안된다.
        request.setAttribute(LOG_ID, uuid);

        // @RequsetMapping: HanlderMethod
        // 정적 리소스: ResourceHttpRequestHandler
        if (handler instanceof HandlerMethod) {
            HandlerMethod hm = (HandlerMethod) handler;  // 호출할 핸들러의 모든 메서드 정보가 담겨있다.
        }
        log.info("REQUEST>>>>>>>>>> [{}][{}][{}]", uuid, requestURI, handler);
        return true;*/

        String requestURI = request.getRequestURI();
        log.info("인증체크 인터셉터 실행{}", requestURI);
        //현재 요청과 연관된 세션을 반환합니다.
        // 만약 세션이 이미 존재하지 않으면(null), 새로운 세션을 생성하지 않고 null을 반환
        HttpSession session = request.getSession(false);

        if (session == null ) {
            log.info("미인증 사용자");
        return false;
    }

        log.info("==================== START ====================");
        log.info(" Request URI \t: " + request.getRequestURI());
        return true;

        //return HandlerInterceptor.super.preHandle(request, response, handler);
    }

/*
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("postHandler [{}]", modelAndView);
    }
*/

   /* @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        String requestURI = request.getRequestURI();
        String uuid = (String) request.getAttribute(LOG_ID);
        log.info("RESPONSE [{}][{}][{}]", uuid, requestURI, handler);
        if (ex != null) {
            log.error("afterCompletion error!!", ex);
        }

    }*/
}

        //return HandlerInterceptor.super.preHandle(request, response, handler);
        //return false;


        /*true 반환: 현재의 인터셉터 이후에 등록된 다음 인터셉터 또는 핸들러 메서드가 계속 실행되도록 허용합니다. 즉, 요청을 계속 진행시킵니다.
        false 반환: 현재의 인터셉터 이후에 등록된 다음 인터셉터 또는 핸들러 메서드가 실행되지 않도록 차단합니다. 즉, 요청을 중단시킵니다.*/


    //postHandle : 컨트롤러를 접근 한 후, 화면으로 결과를 전달하기 전에 실행되는 메서드
    //preHandle()과는 반대로 요청(Request)의끝을 알리는 로그가 콘솔에 출력되도록 처리.
    /*preHandle()- 컨트롤러 호출 전에 호출
    - return 값이 ture면 다음 인터셉터or컨트롤러 호출, false면 더이상 진행하지 않는다.
    postHandle - 컨트롤러 호출 후에 호출
    - 컨트롤러에서 예외 발생 시 호출되지 않는다.
    afterCompletion- 뷰가 렌더링 된 이후에 호출
    - 컨트롤러에서 예외 발생하여도 호출된다.*/



