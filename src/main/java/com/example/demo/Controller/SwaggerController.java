/*
package com.example.demo.Controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class SwaggerController {

    @GetMapping("/swagger{name}")
    public ResponseEntity<String > swagger(@PathVariable(name = "name")String name){   //@PathVariable 어노테이션을 통해서 endpoint 끝에 이름값을 받아 들인다.
        return ResponseEntity.ok(String.format("Hello %s.",name));
        // 결과적으로 ResonseEntity를 생성하고 결과를 반환

    }
}


*/
