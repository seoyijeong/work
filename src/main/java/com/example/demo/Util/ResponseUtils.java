
package com.example.demo.Util;


import com.example.demo.Domain.Dto.ResponseResult;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;

/*컨트롤러를 작성할 때 일일이 응답코드를 설정해주고, ResponseEnity를 리턴해줘야 하는게 번거롭다.
회사에서는 컨트롤러에서 공통으로 쓸 수 있는 클래스를 만들어 상속해서 쓰고 있다.

코딩할 때 공통되는 부분을 최소화 하지 않으면 너무 찝찝해서
최대한 공통되는 코드들은 모아두려고 한다.*/

@Getter
//@NoArgsConstructor
@AllArgsConstructor
public class ResponseUtils {

    //응답코드와 메세지를 지정하고 data를 담은 Response
    public static ResponseEntity<ResponseResult> GetResponseData(Object data){
        //ResponseEntity.ok(ResponseResult.builder().data(board).build())
        return ResponseEntity.ok(ResponseResult.builder()
                        .data(data)
                        .build());
    }

    public static ResponseEntity<ResponseResult> GetResponseData(){
        return  ResponseEntity.ok(ResponseResult.builder().build());
    }

}


