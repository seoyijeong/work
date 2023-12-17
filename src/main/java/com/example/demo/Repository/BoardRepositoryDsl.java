package com.example.demo.Repository;

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

    //Entity : 주로 읽기만 할때(수정은 가능한데 지양함)
    //DTO : 클라이언트나 개발자들이 가공할때 주로 사용 , data는 엔티티로 가져오기만 새로 가공

}
