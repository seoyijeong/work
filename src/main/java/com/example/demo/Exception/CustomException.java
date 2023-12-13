package com.example.demo.Exception;

import com.example.demo.Domain.Dto.ResponseResult;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@Getter
public class CustomException extends RuntimeException{
/*    private HttpStatus httpStatus;
    private String message;*/

    private ErrorCode errorCode;

/*    public CustomException(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }*/

        public CustomException(ErrorCode errorCode) {
            this.errorCode = errorCode;
    }

    //핸들러로 추가 하여 리턴
/*    @ResponseBody
    @ExceptionHandler()
    public static ResponseEntity customException(Exception ex){
        return ResponseEntity
                .ok(ResponseResult.builder()
                        .resultCode("")
                        .msg("")
                        .build());
    }*/
}
