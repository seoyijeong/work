package com.example.demo.Controller;

import com.example.demo.Domain.Dto.ResponseResult;
import com.example.demo.Domain.Entity.MemberEntity;
import com.example.demo.Service.MemberService;
import com.example.demo.Util.ResponseUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/memberList")
public class MemberListController {

    private final MemberService memberService;

    @GetMapping("/getMemberList")
    ResponseEntity<ResponseResult> getMemberList() throws Exception{
        List<MemberEntity> memberEntities = memberService.getMemberList();

        return ResponseUtils.GetResponseData(memberEntities);
    }

}
