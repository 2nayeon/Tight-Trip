package com.travelPlan.domain.member.repository;

import com.travelPlan.domain.member.entity.Member;
import com.travelPlan.domain.member.entity.Role;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    void saveMemberTest() {
        // 1. 가짜 회원 데이터 생성
        Member member = Member.builder()
                .email("test@test.com")
                .nickname("나연")
                .role(Role.USER)
                .build();

        // 2. 저장
        memberRepository.save(member);

        System.out.println("================================");
        System.out.println("회원 가입 테스트 성공!");
        System.out.println("================================");
    }
}