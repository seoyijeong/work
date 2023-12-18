package com.example.demo.Repository;

import com.example.demo.Domain.Dto.MemberDto;
import com.example.demo.Domain.Entity.BoardEntity;
import com.example.demo.Domain.Entity.MemberEntity;
import com.querydsl.jpa.JPQLQueryFactory;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import static com.example.demo.Domain.Entity.QBoardEntity.boardEntity;
import static com.example.demo.Domain.Entity.QMemberEntity.memberEntity;


//BoardRepositoryDsl을 구현한 BoardRepositoryDslImpl
// repositroy = mapper라고 생각하자 --->  DslImpl은 xml과 같음
@Slf4j
public class MemberRepositoryDslImpl implements MemberRepositoryDsl {

    /*  QueryDSL의 JPQLQueryFactory를 사용하여 JPA 쿼리를 생성하고 실행하기 위한 멤버 변수입니다.
    이 멤버 변수는 생성자에서 주입되며, 해당 클래스의 메서드에서 QueryDSL을 활용할 수 있게 합니다.
     */
    protected final JPQLQueryFactory jpqlQueryFactory;  //JPQLQueryFactory : interface

    public MemberRepositoryDslImpl(JPQLQueryFactory jpqlQueryFactory) {

        this.jpqlQueryFactory = jpqlQueryFactory;
    }

    ///////////////로그인
   public MemberEntity memberLogin(MemberDto memberDto){

       return jpqlQueryFactory
               .select(memberEntity)
               .from(memberEntity)
               .where(memberEntity.userId.eq(memberDto.getUserId()))
               .fetchFirst();
   }
    /////////////회원정보 수정
   public MemberEntity memberUpdate(MemberDto memberDto) {
       return jpqlQueryFactory
               .select(memberEntity)
               .from(memberEntity)
               .where(memberEntity.userId.eq(memberDto.getUserId()))
               .fetchFirst();
   }
   ////////////회원 전체 리스트
    public List<MemberEntity> getMemberList() {
        return jpqlQueryFactory.select(memberEntity).from(memberEntity).fetch();
    }
    ////////////멤버 삭제
    @Override
    public void deleteMember(String userId) {
        jpqlQueryFactory.delete(memberEntity)
                .where(memberEntity.userId.eq(userId))
                .execute();
        log.info("member 삭제");
    }
    @Override
    public MemberEntity MemberRead(MemberDto memberDto) {
        log.info("memberRead>>>>>{}", memberDto);
         MemberEntity memberRead= jpqlQueryFactory
                .select(memberEntity)
                .from(memberEntity)
                .where(memberEntity.userId.eq(memberDto.getUserId()))
                 .orderBy(memberEntity.userId.asc())
                .fetchFirst();

    return memberRead;
    }
}
