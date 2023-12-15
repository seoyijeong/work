package com.example.demo.Domain.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/* Serializable
         * 직렬화 : 메모리를 디스크에 저장하거나, [네트워크 통신에 사용하기 위한 형식으로 변환]하는 것
         * 역직렬화 : 디스크에 저장한 데이터를 읽거나, 네트워크 통신으로 받은 데이터를 메모리에 쓸 수 있도록 변환하는 것
         * 직렬화 : 자바 객체를 Byte, CSV, JSON, XML 등 네트워크 통신을 위한 다양한 포맷으로 변환하는 것!
         주로 [자바 객체 -> JSON]을 사용한다.
         * ResponseDto를 HTTP 응답에 담을 때 ResponseDto를 JSON으로 변환하는 과정이 직렬화 과정이고
         JSON HTTP 요청 시 JSON이 RequestDto로 변환되는 과정이 역직렬화 과정
         * HTTP 요청 : @RequestBody, RequestEntity 객체
        * HTTP 응답 : @ResponseBody, ResponseEntity 객체
        * 위와 같은 어노테이션, 객체가 있으면 HttpMessageConverter를 호출해서 변환을 거친다.*/


@Data
@Builder
@AllArgsConstructor
public class ResponseResult implements Serializable {

    protected ResponseResult() {

    }

    //@Builder.Default
//특정 필드를 초기화하고 싶다면 사용하는 @Builder 속성 어노테이션


    @Builder.Default
    String resultCode = "200";
    @Builder.Default
    String msg = "ok";
    @Builder.Default
    Object data = null;
}
