package com.example.demo.Exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/* enum 열거형 상수
* 열거형은 서로 연관된 상수들의 집합
* 자바에서는  final로 String과 같은 문자열이나 숫자들을 나타내는 기본 자료형의 값을
* 고정할수 있음
* 클래스가 상수만으로 작성되어 있으면 반드시 class로 선언할 필요 없음
* 고정된 상수들의 집합으로 컴파일 타임에 모든값을 알고 있어야함. 즉 다른 패키지나 클래스에서
* enum 타입에 접근해서 동적으로 어떤값을 정해 줄 수 없음
* */
@AllArgsConstructor
@Getter
public enum ErrorCode {


    ERR001("ERR001", " 잘못된 경로"),
    ERR002("ERR002", "관리자에게 문의"),
    ERR003("ERR003", "값을 넣어주세요"),
    ERR004("ERR004", "로그인 에러");



    private final String resultCode;
    private final String message;


}

