package com.example.demo.Domain.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;

//Entity
//실제 자료의 구조를 의미
//Entity 애너테이션을 통해 테이블의 이름을 지정할 수 있다.
//사용자가 사용하는 것은 Service이고, 구체적인 구현은 Imp에 있다.
//만약, 다른 형태의 Service로 바꾸고 싶은 경우에 Controller에 있는 소스는 건드릴 필요 없이
//Imp 부분만 건드리면 되기 때문에 업무 로드가 줄어든다.

@Entity  //jpa 디펜던시 추가  //해당 클래스가 데이터베이스에서 엔터티로 매핑되는 것
@Getter
@Setter
@NoArgsConstructor //기본 생성자 생성
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
//PROCTECTED 로 설정하면 아무런 값도 갖지 않는 의미 없는 객체의 생성을 막게 됩니다.
// 즉 무분별한 객체 생성에 대해 한번 더 체크할 수 있습니다.
@AllArgsConstructor // 모든 파라미터의 기본생성자를 생성(모든 필드를 가지는 생성자)
@Builder  //@@AllArgsConstructor 의 생성자를 생성한 후에 @Builder 사용
@Table(name = "member")
public class MemberEntity {

    @Id
    @Column(name = "user_id")
    private String  userId;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "role")
    private String role;

    @Column(name = "create_date")
    private LocalDateTime create_date;


}
