package com.example.demo.Domain.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity  //jpa 디펜던시 추가  //해당 클래스가 데이터베이스에서 엔터티로 매핑되는 것
@Data  //롬복
//@NoArgsConstructor //기본 생성자 생성
@Table(name = "TB_COMMENTS ")
public class CommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //데이터베이스의 자동 증가(auto-increment) 컬럼을 사용하여 기본 키 값을 생성하도록 지정
    @Column(name = "IDX")
    private int idx;

//  @OneToOne
//  @JoinColumn(name = "PARENT_IDX")  //컬럼 연결
    @Column(name = "PARENT_IDX")
    private Integer parentIdx;

//    @Column(name = "COM_TYPE")
//    private String comType;
//
//    @Column(name = "REF_KEY")
//    private String refKey;


    //null값이므로 필요한 아이디만 남김
//    @Column(name = "UID")
//    private int uid;

    @Column(name = "COMMENT")
    private String comment;

//    @Column(name = "DEL_YN")
//    private String delYn;

//    @Column(name = "REG_ID")
//    private String regId;
//
//    @Column(name = "REG_DT")
//    private String redDt;

//    @Column(name = "UPD_ID")
//    private String updId;

//    @Column(name = "UPD_DT")
//    private String updDt;

}
