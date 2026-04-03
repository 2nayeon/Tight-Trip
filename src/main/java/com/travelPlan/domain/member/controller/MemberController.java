package com.travelPlan.domain.member.controller;

import com.travelPlan.domain.member.dto.MemberRequestDto;
import com.travelPlan.domain.member.dto.LoginRequestDto;
import com.travelPlan.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody MemberRequestDto requestDto){
        memberService.join(requestDto);
        return ResponseEntity.ok("회원가입이 완료되었습니다.");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequestDto requestDto){
        try {
            Long memberId = memberService.login(requestDto);
            return ResponseEntity.ok("로그인 성공(회원ID:" + memberId + ")");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestBody LoginRequestDto requestDto){
        return ResponseEntity.ok("로그아웃 되었습니다.");
    }

}
