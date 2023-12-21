package com.example.demo.Token;

import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


public class JwtTokenFilterConfigurer extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    private JwtTokenProvider jwtTokenProvider;

    public JwtTokenFilterConfigurer(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        ////필터가 작동이 하도록 설정해줌
        JwtTokenFilter customFilter = new JwtTokenFilter(jwtTokenProvider);
        //필터를 적용하기 전에  customFilter가 작동하도록 씌워줌
        http.addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter.class);
    }

}

