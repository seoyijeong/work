package com.example.demo.Repository;

import com.example.demo.Domain.Dto.BoardDto;
import com.example.demo.Domain.Dto.CommentDto;
import com.example.demo.Domain.Entity.BoardEntity;

import java.util.List;

public interface BoardRepositoryDsl{
    List<BoardEntity> findBoardList();
    //List<entity  클래스명> findAllByIdIn(List<데이터타입> 기본키);
    //List<TbBoardEntity> findAllByIdIn(List<Integer> IDX);

    List<CommentDto.replyComment> findBoardList(Integer parentIdx);

    CommentDto.comment findComment(Integer idx);


    void deletePost(Integer idx);

}
