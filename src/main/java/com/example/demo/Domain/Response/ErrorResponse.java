package com.example.demo.Domain.Response;

import com.example.demo.Exception.CustomException;
import com.example.demo.Exception.ErrorCode;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

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
