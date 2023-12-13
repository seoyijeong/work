/*
package com.example.demo.Domain.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.net.http.HttpResponse;

//공통 응답 만들기(ResponseEntity)
*/
/* RestApi 응답형식에는 표준이 존재하지 않음
* 표준은 존재하지 않지만 response 형식은 크데 2가지 형태로 사용됨
* 1. Http Status Code + Json Body를 사용하는 방식
*   성공과 오류 응답 모두 http status code 와  json body를 함께 사용
*   기존 http 상태 코드라는 표준을 기반으로 응답 데이터를 추가한 것이디 때문에 어느정도 표준의 영역에 있다고 볼수 잇음


//응답 포맷 지정
//응답을 보낼때 상태코드와 메시지만 띄워주고 싶다. ResponseEntity 의  body에 넣어줄 객체를 만들어주자

@Getter
@AllArgsConstructor
@Builder
public class ResultDto<T> implements Serializable {
    private final String  status;  //"resultCode": "200" /"resultCode:400"
    private final String message; //"message :"success, fail
    private final Object resultData; // "data : "[] or ,{} or ,"ok","fail"


    public ResultDto(final String  status, final String message){
        this.status = status;
        this.message = message;
        this.resultData = null;
    }


    public static<T> ResultDto<T> res(final String status, final String message) {
        return res(status, message, null);
    }

    public static<T> ResultDto<T> res(final String status, final String message, final Object obj) {
        return ResultDto.<T>builder()
                .status(status)
                .message(message)
                .resultData(obj)
                .build();
    }

    public static <T> ResultDto<T> err(final String status, final String message){
        return ResultDto.<T>builder()
                .status(status)
                .message(message)
                .build();
    }
}
*/






