package com.example.demo.Repository;

import com.example.demo.Domain.Entity.MemberEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface MemberRepositoryDsl {
    //@Transactional(readOnly = true)
    List<MemberEntity> findMemberList(String userId);

    void deleteMember(String userId);

//    List<MemberEntity> findMemberList(String userId);
}
