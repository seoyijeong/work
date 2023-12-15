package com.example.demo.Domain.Response;

import com.example.demo.Exception.CustomException;
import com.example.demo.Exception.ErrorCode;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/*static :
1. 메모리에 고정적으로 할당된다
Static이 붙지 않은 메서드나 변수의 경우 객체가 생성될 때마다 호출되어 서로 다른 값을 가지고 있을 수 있습니다.
그렇기 때문에 각 객체들에서 공통적으로 하나의 값이 유지되어야 할 경우 static을 유용하게 사용할 수 있습니다.
2. 객체 생성없이 사용할 수 있다.
어떤 객체에 소속되어있다는 뜻은 new 키워드를 이용하여 객체를 생성하여야만 해당 변수나 메서드를 사용할 수 있다는 뜻*/


@Getter
@Builder
@RequiredArgsConstructor
public class ErrorResponse {
    private final String resultCode;
    private final String code;
    private final String message;

    public ErrorResponse(ErrorCode errorCode){
        this.resultCode = errorCode.getResultCode();
        this.code = errorCode.name();
        this.message = errorCode.getMessage();
    }
    public static ResponseEntity<ErrorResponse> error(CustomException ex){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ErrorResponse.builder()
                        .resultCode(ex.getErrorCode().getResultCode())
                        .code(ex.getErrorCode().name())
                        .message(ex.getErrorCode().getMessage())
                        .build());
    }

}
