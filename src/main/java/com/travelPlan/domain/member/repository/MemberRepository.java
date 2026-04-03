package com.travelPlan.domain.member.repository;

import com.travelPlan.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmail(String email);

    Optional<Member> findByMemberId(Long memberId);

    boolean existsByEmail(String email);

    boolean existsByNickname(String nickname);
}
