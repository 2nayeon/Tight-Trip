package com.travelPlan.domain.schedule.service;

import com.travelPlan.domain.member.entity.Member;
import com.travelPlan.domain.member.repository.MemberRepository;
import com.travelPlan.domain.schedule.dto.ScheduleRequestDto;
import com.travelPlan.domain.schedule.entity.Schedule;
import com.travelPlan.domain.schedule.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final MemberRepository memberRepository;
    private final ScheduleRepository scheduleRepository;

    @Transactional
    public Long saveSchedule(ScheduleRequestDto requestdto, Long memberId) {
        Member member = memberRepository.findByMemberId(memberId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));

        Schedule schedule = Schedule.builder()
                .member(member)
                .title(requestdto.getTitle())
                .startDate(requestdto.getStartDate())
                .endDate(requestdto.getEndDate())
                .googleEventId(requestdto.getGoogleEventId())
                .build();

        Schedule saveSchedule = scheduleRepository.save(schedule);

        return saveSchedule.getScheduleId();
    }
}
