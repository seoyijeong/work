package com.example.demo.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

//Spring Security의 각종 설정은 HttpSecurity로 대부분 이루어진다.
//- URL 접근 권한 설정
/*인증전체 흐름에 필요한 Loginm Logout 페이지 인증완료 후 페이지 인증 실패 시 이동 페이지 등등 설정
인증로직을 커스텀하기 위한 커스텀 필터 설정
기타 csrf, 강제 https 호출 등등 거의 모든 설정*/

@Configuration
//(proxyBeanMethods = false) 일부 메서드만 구성된 구성 클래스에서 빈 메서드를 프록시 처리하지 않도록 설정
@EnableWebSecurity   // Spring Security 설정 활성화
@EnableMethodSecurity
@RequiredArgsConstructor  //final 의 생성자롤 자동생성
public class SecurityConfig {

    //deprecated 클래스
    //메서드 체이닝으로 해결하지말고 앞으로는 람다식을 사용하여서 해결!!!
    //특정 HTTP 요청에 대한 웹 기반 보안 구성
    @Bean
    protected SecurityFilterChain webSecurityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable);
        http
                .authorizeHttpRequests(authz -> authz
                                .requestMatchers("/login").permitAll()
                                .anyRequest().authenticated()
                )
                                .formLogin(formLogin -> formLogin
                                            .loginPage("/login")  //Url안에 주소를 설정해주면 해당 URL로 진입 시 Spring Security가 로그인 기능을 위임받아서 처리
                                            //.defaultSuccessUrl("/")
                                        //사용자가 로그인하기 전에 방문했던 페이지가 아닌, 다른 페이지를 원한다면 defaultSuccessUrl을 사용 //redirect
                                )
                                .logout(logout -> logout.logoutSuccessUrl("/logout")
                                                        .invalidateHttpSession(true)

                );
        return http.build();

    }
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer(){
        return (web -> web.ignoring().anyRequest());
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

  /*  @Autowired   //로그인 처리 구현 : AuthenticationManagerBuilder를 주입해서 인증에 대한 처리를 해야 한다.
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(userService)
                .passwordEncoder(userService.passwordEncoder());
    }*/


    //사용자가 누군지 인증을 받는 것
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }


}
