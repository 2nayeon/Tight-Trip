package com.travelPlan.domain.schedule.entity;

import com.travelPlan.domain.place.entity.Place;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Table(name = "schedule_detail")
public class ScheduleDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "detail_id")
    private Long detailId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "schedule_id", nullable = false)
    private Schedule schedule;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "place_id", nullable = false)
    private Place place;

    @Column(name = "travel_date", nullable = false)
    private LocalDate travelDate;

    @Column(name = "visit_order", nullable = false)
    private int visitOrder;

    @Column(name = "visit_time")
    private LocalTime visitTime;
}
