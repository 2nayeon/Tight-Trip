package com.travelPlan.domain.member.service;

import com.travelPlan.domain.member.dto.MemberRequestDto;
import com.travelPlan.domain.member.dto.LoginRequestDto;
import com.travelPlan.domain.member.entity.Member;
import com.travelPlan.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true) // 읽기 전용으로 설정
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public Long join(MemberRequestDto requestDto) {
        validateDuplicateMember(requestDto.getEmail());
        String password = requestDto.getPassword();

        return memberRepository.save(requestDto.toEntity(password)).getMemberId();
    }

    public Long login(LoginRequestDto requestDto) {
        Member member = memberRepository.findByEmail(requestDto.getEmail())
                .orElseThrow(() -> new IllegalStateException("가입되지 않은 이메일입니다."));

        if (!member.getPassword().equals(requestDto.getPassword())) {
            throw new IllegalStateException("비밀번호가 일치하지 않습니다.");
        }

        return member.getMemberId();
    }

    private void validateDuplicateMember(String email) {
        if(memberRepository.existsByEmail(email)) {
            throw new IllegalStateException("이미 존재하는 이메일입니다.");
        }
    }

}
