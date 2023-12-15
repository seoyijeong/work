package com.example.demo.Repository;

import com.example.demo.Domain.Dto.CommentDto;
import com.example.demo.Domain.Entity.BoardEntity;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQueryFactory;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import static com.example.demo.Domain.Entity.QCommentEntity.commentEntity;

//직접 import 선언해주고.query table 명칭을 새로 만들어준다.(클래스파일은 만들필요 없음)
// QueryDSL에서 코드 생성된 Q 타입을 static import하여 사용
/*Q 타입은 엔티티의 필드들을 정적 상수 형태로 가지고 있어서 타입 안전한 쿼리 작성을 가능케 합니다.*/
import static com.example.demo.Domain.Entity.QBoardEntity.boardEntity;   //query table 명칭

//Querydsl은 미리 작성한 쿼리타입 클래스를 사용해서 쿼리를 메소드 기반으로 작성할 수 있도록 도와주는 프레임워크
// 문자열로 작성하거나 XML 파일에 쿼리를 작성하는 대신 Querydsl이 제공하는 메소드를 사용해서 쿼리를 작성

//BoardRepositoryDsl을 구현한 BoardRepositoryDslImpl
// repositroy = mapper라고 생각하자 --->  DslImpl은 xml과 같음
@Slf4j
public class BoardRepositoryDslImpl implements BoardRepositoryDsl{

    /*  QueryDSL의 JPQLQueryFactory를 사용하여 JPA 쿼리를 생성하고 실행하기 위한 멤버 변수입니다.
    이 멤버 변수는 생성자에서 주입되며, 해당 클래스의 메서드에서 QueryDSL을 활용할 수 있게 합니다.
     */
    protected final JPQLQueryFactory jpqlQueryFactory;  //JPQLQueryFactory : interface

    public BoardRepositoryDslImpl(JPQLQueryFactory jpqlQueryFactory) {

        this.jpqlQueryFactory = jpqlQueryFactory;
    }

    @Override
    public List<BoardEntity> findBoardList() {
        //SELECT * FROM TB_BOARD; QUERY문
        //boardEntity는 QueryDSL이 자동으로 생성한 엔티티에 대한 Q 타입
        /*select(boardEntity)는 엔티티를 선택하고, from(boardEntity)는 해당 엔티티를 어떤 테이블에서
        조회할 것인지를 지정합니다. fetch()는 쿼리를 실행하고 결과를 리스트로 반환*/
        return jpqlQueryFactory.select(boardEntity).from(boardEntity).fetch();
    }

    //projection :프로젝션이란 JPQL의 Select 절을 이용하여 어떤 데이터를 조회할 것인가를 말함
    @Override
    public List<CommentDto.replyComment> findBoardList(Integer parentIdx) {
        log.info("findBoardList");
        return jpqlQueryFactory.select(
                Projections.fields(
                        CommentDto.replyComment.class,
                        //select 항목을 원하는 객체 타입으로 받을때 해당하는 타입으로 선언을 해주는것
                        commentEntity.idx,
                        commentEntity.parentIdx,
                        commentEntity.comment
                )
        )
                .from(commentEntity)
                .where(commentEntity.parentIdx.eq(parentIdx))
                .fetch();   //list
    }
        // CommentDto.comment : 게시판정보 + 댓글정보리스트
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
                .fetchFirst();  //list 아닌것
    }

    @Override
    public void deletePost(Integer idx) {
         jpqlQueryFactory.delete(boardEntity)
                 .where(boardEntity.idx.eq(idx))
                 .execute(); //삭제실행
        log.info("포스트 삭제");
    }

    //데이터를 2번 부를때
    //게시글 테이블 데이터를 부르고, 댓글 데이터 호출 -->service 에서 가공한 데이터를  return

    //SELECT * FROM TB_BOARD; QUERY문
    //boardEntity는 QueryDSL이 자동으로 생성한 엔티티에 대한 Q 타입
        /*select(boardEntity)는 엔티티를 선택하고, from(boardEntity)는 해당 엔티티를 어떤 테이블에서
        조회할 것인지를 지정합니다. fetch()는 쿼리를 실행하고 결과를 리스트로 반환*/

}
