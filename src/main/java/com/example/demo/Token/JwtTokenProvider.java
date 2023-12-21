package com.example.demo.Token;
//jwt 구조
// header : 토큰타입이나 서명 생성에 사용되는 알고리즘을 저장
// payload : 토큰에 담을 정보를 key -value  형태로 저장
// signature : 암호화 되어있는 서명이 저장. 서버에 있는 개인 키로만 복호화가 가능
//(특정 암호화 알고리즘을 사용하여, Base64 인코딩된 헤더와 Base64 인코딩된 페이로드 그리고 비밀키를 이용하여 암호화한다.
// 서명을 통해 서버는 헤더 혹은 페이로드가 누군가에 의해 변조되었는지 그 무결성을 검증하고 보장)
//빌터 패턴을 이용해 jwt생성 -> 키 생성 -> 토큰 출력
// 프론트에서 header 에 담아 넘겨준 상황이라면 jwt 토큰을 추출할 수 있다. -> jwt 토큰안의 데이터를 파싱(이때 생성에 사용된 비밀키 사용)
//claim 파싱이 되면 jwt 토큰 생성에 추가하였던 값을 함수로 추출 가능

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

    private final MemberDetails memberDetails;

    @Value("${jwt.token.key}")
    private String secretKey;

    //토큰 유효시간 설정
    private Long tokenValidTime = 60 * 60 * 1000L;  //1시간


    //secretkey를 미리 인코딩 해줌.
    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    //JWT 토큰 생성
    public String createToken(String userId) {

        //payload 설정
        //registered claims
        Date now = new Date();
        Claims claims = Jwts.claims()
                .setSubject("access_token") //토큰제목
                .setIssuedAt(now) //발행시간
                .setExpiration(new Date(now.getTime() + tokenValidTime)); // 토큰 만료기한

        //private claims
        claims.put("userId", userId); // 정보는 key - value 쌍으로 저장.

        return Jwts.builder()
                .setHeaderParam("typ", "JWT") //헤더
                .setClaims(claims) // 페이로드
                .signWith(SignatureAlgorithm.HS256, secretKey)  // 서명. 사용할 암호화 알고리즘과 signature 에 들어갈 secretKey 세팅
                .compact();
    }

    //JWT 토큰에서 인증정보 조회
    public Authentication getAuthentication(String token) {
        UserDetails userDetails = memberDetails.loadUserByUsername(this.getUserPk(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    // 토큰에서 회원 정보 추출
    public String getUserPk(String token) {
        return (String) Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().get("userId");
    }

    // Request의 Header에서 token 값을 가져옵니다. "Authorization" : "TOKEN값'
    public String resolveToken(HttpServletRequest request) {
        return request.getHeader("JWT");
    }

    // 토큰의 유효성 + 만료일자 확인  // -> 토큰이 expire되지 않았는지 True/False로 반환해줌.
    public boolean validateToken(String jwtToken) {
        try {
            Claims claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwtToken).getBody();

            return !claims.getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }
}