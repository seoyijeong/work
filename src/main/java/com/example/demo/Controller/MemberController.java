package com.example.demo.Controller;

import com.example.demo.Domain.Dto.MemberDto;
import com.example.demo.Domain.Dto.ResponseResult;
import com.example.demo.Domain.Entity.MemberEntity;
import com.example.demo.Service.MemberService;
import com.example.demo.Util.ResponseUtils;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//modelAttribute
/*@ModelAttribute는 클라이언트로부터 일반 HTTP 요청 파라미터나 multipart/form-data 형태의 파라미터를 받아 객체로
사용하고 싶을 때 이용된다.
@ModelAttribute는 "가장 적절한" 생성자를 찾아 객체를 생성 및 초기화한다.
객체 생성 및 초기화 > Data Binding > Validation 순서로 진행된다.
Data Binding은 getter/setter가 존재하는 변수에 한해서 이루어진다.*/

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
    @PostMapping ("/login")
    public ResponseEntity<ResponseResult> memberLogin(@RequestBody MemberDto memberDto)
            throws Exception{
        MemberEntity memberLogin = memberService.memberLogin(memberDto);

        return ResponseUtils.GetResponseData(memberLogin);
    }

    ///////////////////회원 정보 수정
    //1. 로그인이 되어 있는 상태에서 정보를 수정
    //2. restApi를 통해 관련 정보를 수정하고 이를 DB에 업데이트
    @PostMapping("/memberUpdate")
    public ResponseEntity<ResponseResult> updatePost(@RequestBody MemberDto memberDto) {
        memberService.memberRegister(memberDto);
        return ResponseUtils.GetResponseData();
    }

    //////////////상세 조회  XXXX
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