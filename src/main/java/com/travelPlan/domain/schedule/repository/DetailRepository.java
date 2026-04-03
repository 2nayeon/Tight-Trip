package com.travelPlan.domain.schedule.repository;

import com.travelPlan.domain.schedule.entity.ScheduleDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DetailRepository extends JpaRepository<ScheduleDetail, Long> {
}
