package com.example.demo.Service;

import com.example.demo.Domain.Dto.BoardDto;
import com.example.demo.Domain.Dto.CommentDto;
import com.example.demo.Domain.Entity.BoardEntity;
import com.example.demo.Domain.Entity.CommentEntity;
import com.example.demo.Repository.BoardRepository;
import com.example.demo.Repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
//전체 로직
/*비즈니스 로직이란 데이터를 변경, 조작, 저장 등 데이터를 다루는 부분*/
//Service에서는 Repository와 소통하며 데이터를 DB에 넣거나 가져온다.
//Service의 또다른 존재 이유는 재사용성

/*
1.Client가 Request를 보낸다.(Ajax, Axios, fetch등..)
2.Request URL에 알맞은 Controller가 수신 받는다. (@Controller , @RestController)
3.Controller 는 넘어온 요청을 처리하기 위해 Service 를 호출한다.
4.Service는 알맞은 정보를 가공하여 Controller에게 데이터를 넘긴다.
5.Controller 는 Service 의 결과물을 Client 에게 전달해준다.*/

@Service
@RequiredArgsConstructor    //final 선언 어노테이션
@Slf4j
public class BoardService {

    private final BoardRepository boardRepository;
    private final CommentRepository commentRepository;

    @Transactional
    public List<BoardEntity> getBoardList() {
        System.out.println("서비스 보드리스트 도착!");
        return boardRepository.findBoardList();
    }

    @Transactional  //순차적 실행(은행 계좌이체) : 값이 제대로 실행이 안되면 전체 실행불가
    public CommentDto.comment getCommentList(Integer idx) {
        System.out.println(" commentDto.comment service 도착!");
        CommentDto.comment boardComment = boardRepository.findComment(idx);  //게시글+댓글
        List<CommentDto.replyComment> selectBoardList = boardRepository.findBoardList(idx);  //댓글

        //DTO에서 가져온 리스트의 정보 boardComment, List를 합쳐주는 작업
        boardComment.setReply(selectBoardList);

        log.info("댓글 정보 확인{}",selectBoardList);
        return boardComment ;
    }


/*    builder()
    생성자에 들어갈 매개 변수를 메서드로 하나하나 받아들이고 마지막에 통합 빌드해서 객체를 생성하는 방식*/

    //신규 게시글 , 게시글 수정저장
    @Transactional
    public void savePost(BoardDto boardDto) {
        BoardEntity.BoardEntityBuilder builder = BoardEntity
                .builder()
                .title(boardDto.getTitle())
                .content(boardDto.getContent())
                .replyCnt(boardDto.getReplyCnt());

        //신규는 idx가 없기 때문에 save
        // 수정은 기존idx를 불러와서 다시 idx에 저장
        if(boardDto.getIdx() > 0) {
            builder.idx(boardDto.getIdx());
        }
//        BoardEntity post= BoardEntity.builder()
//                .title(boardDto.getTitle())
//                .content(boardDto.getContent())
//                .replyCnt(boardDto.getReplyCnt())
//                .build();
        boardRepository.save(builder.build());

        log.info("게시글 저장{}", builder.build());
    }

    //게시글 수정
//    @Transactional
//    public void updatePost(BoardDto boardDto) {
////         BoardEntity boardEntity =boardRepository.findById(boardDto.getIdx())
////                 .orElseThrow();  //null 값 처리
////        //boardEntity.setIdx(boardDto.getIdx()); id는 pk로 변경되면 안됨
////        boardEntity.setTitle(boardDto.getTitle());  //dto 가져와서 변경
////        boardEntity.setContent(boardDto.getContent());
////        boardEntity.setReplyCnt(boardEntity.getReplyCnt());
//        BoardEntity post= BoardEntity.builder()
//                .idx(boardDto.getIdx())
//                .title(boardDto.getTitle())
//                .content(boardDto.getContent())
//                .replyCnt(boardDto.getReplyCnt())
//                .build();
//        boardRepository.save(post);


    @Transactional
    public void deletePost(Integer idx) {
        boardRepository.deletePost(idx);
    }
    //builder패턴이 안될때는 Entity에서 @Builder 어노테이션 먼저 생성
    @Transactional
    public void saveReply(CommentDto.replyComment replyComment) {
        CommentEntity comment = CommentEntity.builder()
                .parentIdx(replyComment.getParentIdx())
                //.idx(replyComment.getIdx())
                .comment(replyComment.getComment())
                .build();

        commentRepository.save(comment); //The given id must not be null] with root cause (id 값이없음)

    }
    //1.controller에서 requestBody로 전달된 파라미터 값이 넘어옴
    //2.서비스는 컨트롤러에서 넘어온 파라미터 값과 동일한 pk를 찾음
    //3.requestBody로 저장된 값을 Entity에 새로 세팅(pk값으로 찾아온 entity와
    // 새로 업데이트된 값을 새로 저장) --> save 하지 않아도 자동 업데이트

    //boardRepository.save(); --> db에 데이터가 없으면 저장, 존재할때는 업데이트
    //model은 화면에 보여주기 위함(view로 전달될 값이 있을때)

}
