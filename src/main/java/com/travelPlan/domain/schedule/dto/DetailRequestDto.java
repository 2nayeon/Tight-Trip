package com.travelPlan.domain.schedule.dto;

import com.travelPlan.domain.place.entity.Place;
import com.travelPlan.domain.schedule.entity.Schedule;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DetailRequestDto {
    private Long scheduleId;
    private Long placeId;
    private LocalDate travelDate;
    private int visitOrder;
    private LocalTime visitTime;
}
