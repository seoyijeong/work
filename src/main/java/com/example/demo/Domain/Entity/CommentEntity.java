package com.example.demo.Domain.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor //기본 생성자 생성
@AllArgsConstructor // 모든 파라미터의 기본생성자를 생성
@Entity  //jpa 디펜던시 추가  //해당 클래스가 데이터베이스에서 엔터티로 매핑되는 것
@Builder  //@@AllArgsConstructor 의 생성자를 생성한 후에 @Builder 사용
@Data  //롬복
//@NoArgsConstructor //기본 생성자 생성
@Table(name = "TB_COMMENTS")
public class CommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //데이터베이스의 자동 증가(auto-increment) 컬럼을 사용하여 기본 키 값을 생성하도록 지정
    @Column(name = "IDX")
    private int idx;

//  @OneToOne
//  @JoinColumn(name = "PARENT_IDX")  //컬럼 연결
    @Column(name = "PARENT_IDX")
    private Integer parentIdx;


    @Column(name = "COMMENT")
    private String comment;


}
