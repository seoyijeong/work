package com.example.demo.Token;
import com.example.demo.Exception.CustomException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JwtTokenFilter extends OncePerRequestFilter {
    /* OncePerRequestFilter
    JWT(JSON Web Token) 기반의 인증 필터입니다.
    주로 Spring Security의 필터 체인에서 특정 엔드포인트에 대한 요청을 처리하고, 해당 요청의 헤더에서 JWT를 추출하여 검증하고, \
    유효한 경우 해당 토큰으로부터 사용자를 인증하는 역할*/

    private JwtTokenProvider jwtTokenProvider;

    public JwtTokenFilter(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    /*ServletException은 주로 서블릿 작업 중에 예외가 발생했을 때 throw되는 예외 클래스
    이 예외는 보통 웹 어플리케이션에서 서블릿이나 필터, 리스너 등에서의 작업 중 예기치 않은 문제가 발생했을 때 던져지며,
    예외가 발생했음을 웹 컨테이너에 알리고 적절한 오류 응답을 클라이언트에게 전송할 때 사용*/

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain)
            throws ServletException, IOException {
        String token = jwtTokenProvider.resolveToken(httpServletRequest);   //토큰이 null이 아니고, jwtTokenProvider에 의해 검증되면,
        try {
            if (token != null && jwtTokenProvider.validateToken(token)) {
                Authentication auth = jwtTokenProvider.getAuthentication(token);
                SecurityContextHolder.getContext().setAuthentication(auth);  //Authentication 객체를 얻어와 SecurityContextHolder에 설정
            }
        } catch (CustomException ex) {
            SecurityContextHolder.clearContext();   //보안 컨텍스트를 클리어하고, 즉시 리턴하여 다음 필터 체인이나 서블릿으로 진행하지 않습니다.
//            httpServletResponse.sendError(ex.getHttpStatus().value(), ex.getMessage());
            return;
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

}