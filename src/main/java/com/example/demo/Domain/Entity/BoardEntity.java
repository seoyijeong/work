package com.example.demo.Domain.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity  //jpa 디펜던시 추가  //해당 클래스가 데이터베이스에서 엔터티로 매핑되는 것
@Data  //롬복
@Getter
@Setter
@NoArgsConstructor //기본 생성자 생성
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
//PROCTECTED 로 설정하면 아무런 값도 갖지 않는 의미 없는 객체의 생성을 막게 됩니다.
// 즉 무분별한 객체 생성에 대해 한번 더 체크할 수 있습니다.
@AllArgsConstructor // 모든 파라미터의 기본생성자를 생성(모든 필드를 가지는 생성자)
@Builder  //@@AllArgsConstructor 의 생성자를 생성한 후에 @Builder 사용
@Table(name = "TB_BOARD")
public class BoardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //데이터베이스의 자동 증가(auto-increment) 컬럼을 사용하여 기본 키 값을 생성하도록 지정
    @Column(name = "IDX")
    private int idx;
    @Column(name = "TITLE")
    private String title;

    @Column(name = "CONTENT")
    private String content;

    @Column(name = "REPLYCNT")
    private int replyCnt;

    @Column(name = "REG_ID")
    private String reg_id;

    @Column(name = "REG_DT")
    private String reg_dt;

    @Column(name = "UPD_ID")
    private String upd_id;

    @Column(name = "UPD_DT")
    private String upd_dt;

}

//@Builder.Default
//특정 필드를 초기화하고 싶다면 사용하는 @Builder 속성 어노테이션
