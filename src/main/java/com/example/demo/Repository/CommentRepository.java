package com.example.demo.Repository;


import com.example.demo.Domain.Entity.BoardEntity;
import com.example.demo.Domain.Entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository //액세스 계층의 구성 요소를 나타내기 위해 사용되는 주석, 데이터베이스와 상호 작용하는 코드
public interface CommentRepository
        extends JpaRepository<CommentEntity, Integer>, CommentRepositoryDsl{
}
