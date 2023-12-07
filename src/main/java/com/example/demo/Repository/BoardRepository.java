package com.example.demo.Repository;

import com.example.demo.Domain.Dto.BoardDto;
import com.example.demo.Domain.Entity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//extends JpaRepository<TbBoardEntity, Integer> ->JapRepository
// 클래스를 불러와서 사용<Entity 클래스명, pk 데이터타입>
// repositroy = mapper라고 생각하자
@Repository //액세스 계층의 구성 요소를 나타내기 위해 사용되는 주석, 데이터베이스와 상호 작용하는 코드
public interface BoardRepository
        extends JpaRepository<BoardEntity, Integer>, BoardRepositoryDsl{
    // 쿼리문 넣기

        //findAllByIdIn 메서드 정의
        /*findAll: 모든 엔터티를 조회한다.
          ById: 기본 키(ID)를 기반으로 조회한다.
          In: 주어진 값들 중에 해당하는 것들을 조회한다.*/
}
