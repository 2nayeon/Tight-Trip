package com.travelPlan.domain.place.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlaceDto {
    private Long placeId;
    private String placeName;
    private String category;
    private String apiSource;
    private String apiId;
    private String address;
    private BigDecimal latitude;
    private BigDecimal longitude;
}
