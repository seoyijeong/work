package com.example.demo.Service;

import com.example.demo.Domain.Dto.MemberDto;
import com.example.demo.Domain.Entity.MemberEntity;
import com.example.demo.Exception.CustomException;
import com.example.demo.Exception.ErrorCode;
import com.example.demo.Repository.MemberRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Member;
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

    ///////////////////////회원가입
    @Transactional
    public void memberRegister(MemberDto memberDto) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        MemberEntity member = MemberEntity.builder()
                .userId(memberDto.getUserId())
                .userName(memberDto.getUserName())
                .email((memberDto.getEmail()))
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
    //MemberDto memberDto 사용자가 입력한 값
    public MemberEntity memberLogin(MemberDto memberDto) throws Exception {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        //아이디와 일치하는 회원정보를 Entity에 담아서 가져옴
        //쿼리를 호출하는 메서드 find , memberinfoById
        MemberEntity memberLoginList = memberRepository.memberLogin(memberDto); //얘는 이미 암호화가 되어있음

        //memberDto.getUserId()  사용자가 입력한값

        //아이디가 존재하는 아이디 인지 체크(memberLoginList DB 값)이 null일때
        if(memberLoginList == null) {
            throw new CustomException(ErrorCode.ERR004);
        }
        //if문은 틀린것을 찾아야...가 일반(부정문)
        //id가 이미 DB에 있음
        if (!passwordEncoder.matches(memberDto.getPassword(), memberLoginList.getPassword())) {
            throw new CustomException(ErrorCode.ERR004);
        }
        return memberLoginList;
    }

    /////////////////////////////////////회원정보 수정
    //1. 로그인이 되어 있는 상태에서 정보를 수정
    //비밀번호를 입력하여 회원정보 수정
    //닉네임과 비밀번호를 수정할수 있다
    //중복검사
    //정보가 완료되면 변경된 내용을 보여준다
    //비밀번호만 불러오기 entity =id.  dto =id -->pw 변경시 암호화 번호 동일
    //
    //2. restApi를 통해 관련 정보를 수정하고 이를 DB에 업데이트

    @Transactional
    public MemberEntity memberUpdate(MemberDto memberDto) throws Exception{

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        MemberEntity memberUpdate = memberRepository.memberUpdate(memberDto);

        if (!memberDto.getUserId().equals(memberUpdate.getUserId())){
            throw new CustomException(ErrorCode.ERR001);
        }
        if (!passwordEncoder.matches(memberDto.getPassword(), memberUpdate.getPassword())) {
            throw new CustomException(ErrorCode.ERR004);
        }
        MemberEntity memberUpdate2 = MemberEntity.builder()
                .userId(memberDto.getUserId())
                .userName(memberDto.getUserName())
                .email((memberDto.getEmail()))
                .password(passwordEncoder.encode(memberDto.getPassword()))
                .build();
        memberRepository.save(memberUpdate2);

        log.info(">>>>>>>>>>>>>{} 왔다!!!!", memberUpdate2);
        return memberUpdate;
    }

    public void deleteMember(String userId) {
        memberRepository.deleteMember(userId);
    }
    public List<MemberEntity> getMemberList() {
       return memberRepository.getMemberList();
    }
    //////////////// //////////////회원정보 상세조회

/*    @Transactional
    public List<MemberEntity> getMemberList() throws Exception{
        System.out.println("서비스 보드리스트 도착!");
        int i = 0;
        if(i != 0) { //정상작동
            //    if(i == 0) { //정상으로 작동되지 않을때
            //throw  new customException(해당 enum);
            throw new CustomException(ErrorCode.ERR001);
            //throw new Exception(ResponseEntity.status().body());
        }
        return memberRepository.getMemberList();
    }*/
}