package com.tighttrip.domain.member.dto;

import com.tighttrip.domain.member.entity.Member;
import com.tighttrip.domain.member.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor // 파라미터가 없는 생성자
@AllArgsConstructor // 모든 필드를 인자로 받는 생성자
@Builder
public class MemberRequestDto {

    private String email;
    private String password;
    private String nickName;

    public Member toEntity(String encodedPassword) {
        return Member.builder()
                .email(this.email)
                .password(encodedPassword)
                .nickname(this.nickName)
                .role(Role.USER)
                .provider("native")
                .build();
    }
}
