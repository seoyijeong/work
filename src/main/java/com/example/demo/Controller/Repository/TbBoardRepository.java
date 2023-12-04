package com.example.demo.Controller.Repository;

import com.example.demo.Controller.Entity.TbBoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface TbBoardRepository extends JpaRepository<TbBoardEntity, Integer> {
    // 쿼리문 넣기

        //findAllByIdIn 메서드 정의
        /*findAll: 모든 엔터티를 조회한다.
          ById: 기본 키(ID)를 기반으로 조회한다.
          In: 주어진 값들 중에 해당하는 것들을 조회한다.*/

        //List<entity  클래스명> findAllByIdIn(List<데이터타입> 기본키);
    //List<TbBoardEntity> findAllByIdIn(List<Integer> IDX);
}
