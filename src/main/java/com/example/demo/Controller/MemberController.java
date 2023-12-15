package com.example.demo.Controller;

import com.example.demo.Domain.Dto.MemberDto;
import com.example.demo.Domain.Dto.ResponseResult;
import com.example.demo.Domain.Entity.MemberEntity;
import com.example.demo.Service.MemberService;
import com.example.demo.Util.ResponseUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*") // 기본적으로 '모든 도메인, 모든 요청방식' 에 대해 허용 한다는 뜻
@Slf4j //log처리
@RequiredArgsConstructor  //@Autowired final이 붙거나 @NotNull이 붙은 필드의 생성자를 자동 생성해주는 롬복어노테이션
@RequestMapping("/member") //요청매핑
public class MemberController {

    private final MemberService memberService;

///////////////////// 회원가입
    @PostMapping("/register")
    public ResponseEntity<ResponseResult> memberRegister(@RequestBody MemberDto memberDto)
            throws Exception{
        memberService.memberRegister(memberDto);
        log.info(">>>>>>>>>>>>>{} 왔다!!!!", memberDto);
        return ResponseUtils.GetResponseData();
        }
////////////////////////로그인
    @GetMapping("/login/{userId}")
    public ResponseEntity<ResponseResult> memberLogin(@PathVariable(name = "userId") String userId)throws Exception{
    memberService.memberLogin(userId);
    return ResponseUtils.GetResponseData();

    }

    ///////////////////회원 정보 수정
    @PostMapping("/memberUpdate")
    public ResponseEntity<ResponseResult> updatePost(@RequestBody MemberDto memberDto) {
        memberService.memberRegister(memberDto);
        return ResponseUtils.GetResponseData();
    }

    //////////////상세 조회
    @GetMapping("/read/{userId}")
    public ResponseEntity<ResponseResult> getMemberList(@PathVariable(name = "userId") String userId) {
        List<MemberEntity> read = memberService.getMemberList(userId);
        return ResponseUtils.GetResponseData(read);
    }
    /////////////////삭제
    @Transactional
    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<ResponseResult> deleteMember (@PathVariable String userId) {
        memberService.deleteMember(userId);
        return ResponseUtils.GetResponseData();
    }

}