package com.example.demo.Domain.Dto;

import com.mysql.cj.util.StringUtils;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.sql.Timestamp;
@Data // 회원가입 유저 정보
public class MemberDto {

        private String userId;
        private String userName;
        private String password;

        public void encodingPassword(PasswordEncoder passwordEncoder){
            if(StringUtils.isNullOrEmpty(password)){
                return;
            }
            password = passwordEncoder.encode(password);
        }

    }

