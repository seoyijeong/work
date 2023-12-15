package com.example.demo.Service;

import com.example.demo.Domain.Dto.MemberDto;
import com.example.demo.Domain.Entity.MemberEntity;
import com.example.demo.Exception.CustomException;
import com.example.demo.Exception.ErrorCode;
import com.example.demo.Repository.MemberRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
//전체 로직


/*
1.Client가 Request를 보낸다.(Ajax, Axios, fetch등..)
2.Request URL에 알맞은 Controller가 수신 받는다. (@Controller , @RestController)
3.Controller 는 넘어온 요청을 처리하기 위해 Service 를 호출한다.
4.Service는 알맞은 정보를 가공하여 Controller에게 데이터를 넘긴다.
5.Controller 는 Service 의 결과물을 Client 에게 전달해준다.*/

@Service
@RequiredArgsConstructor    //final 선언 어노테이션
@Slf4j
//@Data : 서비스는 로직을 처리 하는곳이니 data가 필요없음
public class MemberService {

    private final MemberRepository memberRepository;
    //JPA Entity는 스프링 Bean이 아니므로 @Autowired가 아닌 직접 위와 같이 주입해줘야 함

    ////회원가입
    @Transactional
    public void memberRegister(MemberDto memberDto){
        PasswordEncoder passwordEncoder= new BCryptPasswordEncoder();



        MemberEntity member = MemberEntity.builder()
                .userId(memberDto.getUserId())
                .userName(memberDto.getUserName())
                .password(passwordEncoder.encode(memberDto.getPassword()))
                .build();
        memberRepository.save(member);
        log.info(">>>>>>>>>>>>>{} 왔다!!!!", member);
    /*public void memberRegister(MemberDto memberDto) throws Exception{
        if (memberDto.getUserName() == null || memberDto.getUserId() ==
                null || memberDto.getPassword() == null) { //정상작동

            throw new CustomException(ErrorCode.ERR003);
        } //값이 정확하지 않을때 controller에서 받아온 데이터를 저장하지 않고 예외처리로 넘어감
        // if~else, switch, continue 로 수정하기
        // 조건절에 여러개의 구문이 있으면 예외가 잘 발생함

*//*        int i = 0;
        if(i == 0) { //정상작동
            //             if(i == 0) {  //정상으로 작동되지 않았을때
            throw new CustomException(ErrorCode.ERR003);
        }*//*

//        MemberEntity.MemberEntityBuilder memberBuilder = MemberEntity
//                .builder()
//                .userName(memberDto.getUserName())
//                .userId(memberDto.getUserId())
//                .password(memberDto.getPassword());

        MemberEntity member = MemberEntity.builder()
                .userName(memberDto.getUserName())
                .userId(memberDto.getUserId())
                .password(memberDto.getPassword())
                .build();
        //id 중복 체크,로그인
*//*        if(memberDto.getUserId().equals(member.getUserId())) {
            member.setUserId(memberDto.getUserId());
        }*//*
        memberRepository.save(member);
        log.info(">>>>>>>>>>>>>{} 왔다!!!!", member);*/
        //>>>>>>>>>>>>>com.example.demo.Domain.Entity.MemberEntity@2ebbff57 왔다!!!!
        //객체 주소로 들어옴
    }
    ////////////////////로그인
    public void memberLogin(String userId) {

    }

    //회원정보 불러오기
    @Transactional
    public List<MemberEntity> getMemberList(String userId) {
//        memberRepository.memberList(userId);

        return memberRepository.findMemberList(userId);
    }

    public void deleteMember(String userId) {
        memberRepository.deleteMember(userId);
    }


}