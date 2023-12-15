package com.example.demo.Repository;

import com.example.demo.Domain.Entity.BoardEntity;
import com.example.demo.Domain.Entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//extends JpaRepository<TbBoardEntity, Integer> ->JapRepository
// 클래스를 불러와서 사용<Entity 클래스명, pk 데이터타입>
// repositroy = mapper라고 생각하자
//@Repository //액세스 계층의 구성 요소를 나타내기 위해 사용되는 주석, 데이터베이스와 상호 작용하는 코드
public interface MemberRepository
        extends JpaRepository<MemberEntity, String >, MemberRepositoryDsl{
}
