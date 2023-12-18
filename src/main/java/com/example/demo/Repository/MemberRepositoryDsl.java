package com.example.demo.Repository;

import com.example.demo.Domain.Dto.MemberDto;
import com.example.demo.Domain.Entity.MemberEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface MemberRepositoryDsl {
    //@Transactional(readOnly = true)
    List<MemberEntity> findMemberList(String userId);

    void deleteMember(String userId);

    MemberEntity memberLogin(MemberDto memberDto);

    MemberEntity memberUpdate(MemberDto memberDto);

    List<MemberEntity> getMemberList();
}
