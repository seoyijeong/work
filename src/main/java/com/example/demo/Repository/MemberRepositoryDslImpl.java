package com.example.demo.Repository;

import com.example.demo.Domain.Entity.MemberEntity;
import com.querydsl.jpa.JPQLQueryFactory;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

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

    @Override
    public List<MemberEntity> findMemberList(String userId) {
        return jpqlQueryFactory.select(memberEntity)
                .from(memberEntity)
                .where(memberEntity.userId.eq(userId))
                .fetch();
    }

    @Override
    public void deleteMember(String userId) {
        jpqlQueryFactory.delete(memberEntity)
                .where(memberEntity.userId.eq(userId))
                .execute();
        log.info("member 삭제");
    }
}
