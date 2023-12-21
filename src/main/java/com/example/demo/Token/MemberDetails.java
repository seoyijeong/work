package com.example.demo.Token;

import com.example.demo.Domain.Dto.MemberDto;
import com.example.demo.Domain.Entity.MemberEntity;
import com.example.demo.Repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberDetails implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {


        MemberDto memberDto = new MemberDto();
        memberDto.setUserId(username);

        final MemberEntity memberEntity = memberRepository.MemberRead(memberDto);

        if (memberEntity == null) {
            throw new UsernameNotFoundException("UserId '" + memberDto.getUserId() + "' not found");
        }

        return org.springframework.security.core.userdetails.User//
                .withUsername(username)//
                .password(memberEntity.getPassword())//
                .accountExpired(false)//
                .accountLocked(false)//
                .credentialsExpired(false)//
                .disabled(false)//
                .build();
    }

}