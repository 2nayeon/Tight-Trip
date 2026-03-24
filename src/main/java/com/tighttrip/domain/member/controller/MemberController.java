package com.tighttrip.domain.member.controller;

import com.tighttrip.domain.member.dto.MemberRequestDto;
import com.tighttrip.domain.member.service.MemberService;
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
}
