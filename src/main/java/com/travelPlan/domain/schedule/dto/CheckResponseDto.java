package com.travelPlan.domain.schedule.dto;

import com.travelPlan.domain.place.entity.OperatingTime;
import lombok.*;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CheckResponseDto {
    private Boolean isPossible;
    private String message;
    private List<String> recommendations;
}
