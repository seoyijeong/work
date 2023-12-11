package com.example.demo.config;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
/*qudryDsl : java에서 안전한 sql 쿼리를 작성할수 있게 도와주는 라이브러리. 해당 설정파일은 queryDsl을 사용하여
 jpa를 편리하게 활용하기 위해 jpaQueryFactory를 빈(bean)으로 등록하는 역할
 */
@EnableJpaAuditing //엔티티의 생성일, 수정일 등을 자동으로 관리하는 기능을 제공
@Configuration // 해당 클래스가 설정 파일임을 나타냅니다
public class QueryDslConfig {
    @PersistenceContext() //EntityManager를 주입받기 위해 사용
    //public interface EntityManager
    private EntityManager entityManager;


    @Bean //해당 메서드가 Spring 컨테이너에 의해 관리되는 빈을 생성하는 메서드임을 나타냅니다.
    public JPAQueryFactory jpaQueryFactory() {
        return new JPAQueryFactory(this.entityManager);
    }
}
