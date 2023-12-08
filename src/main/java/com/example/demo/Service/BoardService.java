package com.example.demo.Service;

import com.example.demo.Domain.Dto.BoardDto;
import com.example.demo.Domain.Dto.CommentDto;
import com.example.demo.Domain.Entity.BoardEntity;
import com.example.demo.Repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
//전체 로직
/*비즈니스 로직이란 데이터를 변경, 조작, 저장 등 데이터를 다루는 부분*/
//Service에서는 Repository와 소통하며 데이터를 DB에 넣거나 가져온다.
//Service의 또다른 존재 이유는 재사용성
@Service
@RequiredArgsConstructor    //final 선언 어노테이션
@Slf4j
public class BoardService {

    private final BoardRepository boardRepository;

    @Transactional
    public List<BoardEntity> getBoardList() {
        System.out.println("서비스 리스트 도착!");
        return boardRepository.findBoardList();
    }

    @Transactional
    public CommentDto.comment getCommentList(Integer idx) {
        System.out.println(" commentDto.comment service 도착!");
        CommentDto.comment boardComment = boardRepository.findComment(idx);
        List<CommentDto.replyComment> selectBoardList = boardRepository.findBoardList(idx);

        //DTO에서 가져온 리스트의 정보 boardComment, List를 합쳐주는 작업
        boardComment.setReply(selectBoardList);

        log.info("댓글 정보 확인{}",selectBoardList);
        return boardComment ;
    }

    @Transactional
    public void savePost(BoardDto boardDto) {
        BoardEntity post= BoardEntity.builder()
                .title(boardDto.getTitle())
                .content(boardDto.getContent())
                .replyCnt(boardDto.getReplyCnt())
                .build();
        boardRepository.save(post);

        log.info("게시글 저장{}", post);
    }

    @Transactional  //순차적 실행(은행 계좌이체) : 값이 제대로 실행이 안되면 전체 실행불가
    public void updatePost(BoardDto boardDto) {
         BoardEntity boardEntity =boardRepository.findById(boardDto.getIdx())
                 .orElseThrow();  //null 값 처리
        //boardEntity.setIdx(boardDto.getIdx()); id는 pk로 변경되면 안됨
        boardEntity.setTitle(boardDto.getTitle());
        boardEntity.setContent(boardDto.getContent());
        boardEntity.setReplyCnt(boardEntity.getReplyCnt());
    }

    @Transactional
    public void deletePost(Integer idx) {
        boardRepository.deletePost(idx);

    }
    //1.controller에서 requestBody로 전달된 파라미터 값이 넘어옴
    //2.서비스는 컨트롤러에서 넘어온 파라미터 값과 동일한 pk를 찾음
    //3.requestBody로 저장된 값을 Entity에 새로 세팅(pk값으로 찾아온 entity와
    // 새로 업데이트된 값을 새로 저장) --> save 하지 않아도 자동 업데이트

    //boardRepository.save(); --> db에 데이터가 없으면 저장, 존재할때는 업데이트
    //model은 화면에 보여주기 위함

}
