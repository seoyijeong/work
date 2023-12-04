package com.example.demo.Controller.Entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity  //jpa 디펜던시 추가  //해당 클래스가 데이터베이스에서 엔터티로 매핑되는 것
@Data
@NoArgsConstructor //기본 생성자 생성
@Table(name = "TB_BOARD")
public class TbBoardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //데이터베이스의 자동 증가(auto-increment) 컬럼을 사용하여 기본 키 값을 생성하도록 지정
    @Column(name = "IDX")
    private int idx;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "CONTENT")
    private String content;

    @Column(name = "REPLYCNT")
    private int replycnt;

    @Column(name = "REG_ID")
    private String reg_id;

    @Column(name = "REG_DT")
    private String reg_dt;

    @Column(name = "UPD_ID")
    private String upd_id;
    @Column(name = "UPD_DT")
    private String upd_dt;

}
