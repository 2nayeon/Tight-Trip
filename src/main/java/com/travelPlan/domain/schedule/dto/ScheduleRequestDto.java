package com.travelPlan.domain.schedule.dto;

import com.travelPlan.domain.member.entity.Member;
import com.travelPlan.domain.schedule.entity.Schedule;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ScheduleRequestDto {
    private Member member;
    private String title;
    private LocalDate startDate;
    private LocalDate endDate;
    private String googleEventId;
}
