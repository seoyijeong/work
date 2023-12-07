package com.example.demo.Repository;

import com.example.demo.Domain.Dto.CommentDto;
import com.example.demo.Domain.Entity.BoardEntity;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQueryFactory;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import static com.example.demo.Domain.Entity.QBoardEntity.boardEntity;
import static com.example.demo.Domain.Entity.QCommentEntity.commentEntity;

//BoardRepositoryDsl을 구현한 BoardRepositoryDslImpl
@Slf4j
public class CommentRepositoryDslImpl implements CommentRepositoryDsl{

    /*  QueryDSL의 JPQLQueryFactory를 사용하여 JPA 쿼리를 생성하고 실행하기 위한 멤버 변수입니다.
    이 멤버 변수는 생성자에서 주입되며, 해당 클래스의 메서드에서 QueryDSL을 활용할 수 있게 합니다.
     */
    protected final JPQLQueryFactory jpqlQueryFactory;

    public CommentRepositoryDslImpl(JPQLQueryFactory jpqlQueryFactory) {

        this.jpqlQueryFactory = jpqlQueryFactory;
    }
    @Override
    public CommentDto.comment findComment(Integer idx) {
        log.info("findComment 도착");

        return jpqlQueryFactory.select(
                        Projections.fields(
                                CommentDto.comment.class,
                                boardEntity.idx,
                                boardEntity.title,
                                boardEntity.content

                        )

                )
                .from(boardEntity)
                .where(boardEntity.idx.eq(idx))
                .fetchFirst();
    }
    //데이터를 2번 부를때
    //게시글 테이블 데이터를 부르고, 댓글 데이터 호출 -->service 에서 가공한 데이터를  return

    //SELECT * FROM TB_BOARD; QUERY문
    //boardEntity는 QueryDSL이 자동으로 생성한 엔티티에 대한 Q 타입
        /*select(boardEntity)는 엔티티를 선택하고, from(boardEntity)는 해당 엔티티를 어떤 테이블에서
        조회할 것인지를 지정합니다. fetch()는 쿼리를 실행하고 결과를 리스트로 반환*/

}
