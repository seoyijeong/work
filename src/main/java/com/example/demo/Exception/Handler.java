package com.example.demo.Exception;

import com.example.demo.Domain.Dto.ResponseResult;
import com.example.demo.Domain.Response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//ExceptionHandler 는 Controller계층에서 발생하는 에러를 잡아서 메서드로 처리
// service, repository에서 발생하는 에러는 제외

//ControllerAdvice : @Controller 와 handler에서 발생하는 에러들을 모두 잡아줌
//ControllerAdvice 안에서 ExceptionHandeler를 사용하여 에러를 잡을 수 있음
// 일부 에러만 처리하고 싶을 경우에는 따로 설정을 해주면 됨
//1. 어노테이션
//  @ControllerAdvice(annotations = RestController.class)
//   public class ExampleAdvice1 {}
//2. basePackages (+basepackagesClasses)
//  @ControllerAdvice("org.example.controllers")
//  public class ExampleAdvice2 {}
//3. assignableTypes
//  @ControllerAdvice(assignableTypes = {ControllerInterface.class, AbstractController.class})
//  public class ExampleAdvice3 {}
// 주의사항: 어노테이션, 베이스패키지 등 설정자들은 런타임시 수행되기 때문에 너무 많은 설정자를 사용하면 성능이 떨어질수 잇음

//@ControllerAdvice vs @RestControllerAdvice
/* @ControllerAdvice는 @Componenet 어노테이션을 가지고 있어 컴포넌트 스캔을 통해 스프링 빈으로 등록된다.
 @RestControllerAdvice는 @Controlleradvice와 @ResponseBody
어노테이션으로 이루어져있고 HTML 뷰 보다는 Response body로 값을 리턴할 수 있다.*/

/*핸들러는 매니저와 같은 역할을하는 객체정도로 정의가 가능
* 매니저는 식당을 운영을 하는 직업이며, 요리사와 논의후 식자재를 조달하고,직원들에게 알맞은 월급 분배와 고객의 데이터들을 관리하기도 한다.
* 만약 Button의 핸들이라면 버튼의 속성이나 상태를 핸들링 할 수 있는 객체일 것 이고 Button의 핸들러 라면 Button의 매니저를 의미하는 객체이다.
*/

/*handler
*
사용자 정의 예외(Custom Exception)를 처리할 때, 핸들러(handler)의 역할은 예외가 발생했을 때 적절한 대응을 수행하는
* 코드를 정의하는 것입니다. 예외 핸들러는 예외가 발생했을 때 실행될 코드 블록을 정의하며,
* 이를 통해 프로그램이 예외 상황을 적절하게 처리할 수 있습니다.
  사용자 정의 예외를 만들었다면, 해당 예외가 발생했을 때 어떻게 대응할지를 핸들러에서 정의해야 합니다.
* 이는 프로그램의 신뢰성을 높이고 예외 상황에 대한 사용자 정의된 처리를 제공하는 데 도움이 됩니다.*/

@Slf4j
@RestControllerAdvice
public class Handler {
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) //BAD_REQUEST 요청을 받으면 ResponseEntity 가 실행
    @ResponseBody
    @ExceptionHandler(Exception.class)
    //ExceptionHandler 의 value 값을 설정하지 않으면 모든 exception을 잡게 되기 때문에 구체적으로 적어주는것이 좋음
/*    public ResponseEntity handlerException(){
        return new ResponseEntity() ;
    }*/
    public static ResponseEntity handlerException(Exception ex){
        ex.printStackTrace();
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ResponseResult.builder()
                        .resultCode(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR))
                        .msg("서버 에러가 발생헀습니다. 관리자에게 문의하세요.")
                        .build());
    }

/*    @ResponseBody
    @ExceptionHandler(DuplicateKeyException.class)  //중복 키를 참조하고 오류 메시지를 제공
    //ExceptionHandler 의 value 값을 설정하지 않으면 모든 exception을 잡게 되기 때문에 구체적으로 적어주는것이 좋음
*//*    public ResponseEntity handlerException(){
        return new ResponseEntity() ;
    }*//*
    public static ResponseEntity handlerDuplicateKeyException(DuplicateKeyException ex){
        ex.printStackTrace();
        return ResponseEntity
                .ok(ResponseResult.builder()
                       // .resultCode("DUP001")
                        //.msg(ex.getMessage())
                        .resultCode(ErrorCode.ERR001.name())
                        .msg(ErrorCode.ERR001.getMessage())
                        .build());
    }
    @ExceptionHandler(NullPointerException.class)
    public static ResponseEntity handlerDuplicateKeyException(NullPointerException ex){
        ex.printStackTrace();
        return ResponseEntity
                .ok(ResponseResult.builder()
                        // .resultCode("DUP001")
                        //.msg(ex.getMessage())
                        .resultCode(ErrorCode.ERR002.name())
                        .msg(ErrorCode.ERR002.getMessage())
                        .build());
    }*/

    @ExceptionHandler(value = CustomException.class)
    public static ResponseEntity<ResponseResult> customException(CustomException ex){
        log.error("[CustomException] {}: {}", ex.getErrorCode().getMessage(),ex.getMessage());
        return ResponseEntity.
                ok(ResponseResult.builder()
                        .msg(ex.getErrorCode().getMessage())
                        .resultCode(ex.getErrorCode().getResultCode())
                        .build());
    }
}
