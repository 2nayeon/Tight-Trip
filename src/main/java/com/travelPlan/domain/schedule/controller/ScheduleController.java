package com.travelPlan.domain.schedule.controller;

import com.travelPlan.domain.schedule.dto.CheckResponseDto;
import com.travelPlan.domain.schedule.dto.DetailRequestDto;
import com.travelPlan.domain.schedule.dto.ScheduleRequestDto;
import com.travelPlan.domain.schedule.entity.Schedule;
import com.travelPlan.domain.schedule.service.ScheduleDetailService;
import com.travelPlan.domain.schedule.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequiredArgsConstructor // 생성자 주입 자동화
@RequestMapping("/api/trip")
public class ScheduleController {

    private final ScheduleService scheduleService;
    private final ScheduleDetailService scheduleDetailService;


    @PostMapping("/schedules")
    public ResponseEntity<Long> addSchedule(@RequestBody ScheduleRequestDto requestDto) {
        Long memberId = 1L;
        Long scheduleId = scheduleService.saveSchedule(requestDto, memberId);

        return ResponseEntity.ok(scheduleId);
    }

    @PostMapping("/details")
    public ResponseEntity<List<Long>> addDetails(@RequestBody List<DetailRequestDto> requestDtoList) {
        List<Long> scheduleDetails = scheduleDetailService.saveDetails(requestDtoList);

        return ResponseEntity.ok(scheduleDetails);
    }

    @GetMapping("/check")
    public ResponseEntity<CheckResponseDto> checkOperatingTime(
        @RequestParam Long placeId,
        @RequestParam @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate travelDate,
        @RequestParam @DateTimeFormat(pattern="HH:mm:ss") LocalTime visitTime
    ) {
        CheckResponseDto isPossible= scheduleDetailService.checkPossible(placeId, travelDate, visitTime);

        return ResponseEntity.ok(isPossible);
    }
}
