package com.travelPlan.domain.place.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Table(name = "place")
public class Place {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "place_id")
    private Long placeId;

    @Column(name = "place_name", nullable = false, length = 255)
    private String placeName;

    @Column(length = 50)
    private String category;

    @Column(name = "api_source", length = 20)
    private String apiSource;

    @Column(name = "api_id", unique = true, length = 100)
    private String apiId;

    @Column(length = 500)
    private String address;

    @Column(precision = 13, scale = 10)
    private BigDecimal latitude;

    @Column(precision = 13, scale = 10)
    private BigDecimal longitude;

    @OneToMany(mappedBy = "place", cascade = CascadeType.ALL)
    private List<OperatingTime> operatingTimes = new ArrayList<>();

    @Column(name = "created_at", updatable = false, insertable = false,
            columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;
}
