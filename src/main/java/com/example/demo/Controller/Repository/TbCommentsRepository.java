package com.example.demo.Controller.Repository;

import com.example.demo.Controller.Entity.TbBoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

//extends JpaRepository<TbBoardEntity, Integer> ->JapRepository 클래스를 불러와서 사용<Entity 클래스명, pk 데이터타입>
public interface TbCommentsRepository  {
    //쿼리 호출해서 작성

}
