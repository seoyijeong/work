package com.example.demo.Repository;

import com.example.demo.Domain.Dto.MemberDto;
import com.example.demo.Domain.Entity.MemberEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface MemberRepositoryDsl {

    void deleteMember(String userId);

    MemberEntity memberLogin(MemberDto memberDto);

    MemberEntity memberUpdate(MemberDto memberDto);

    MemberEntity MemberRead(MemberDto memberDto);

    List<MemberEntity> getMemberList();

}
