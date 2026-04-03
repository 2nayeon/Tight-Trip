package com.travelPlan.domain.schedule.service;

import com.travelPlan.domain.place.entity.OperatingTime;
import com.travelPlan.domain.place.entity.Place;
import com.travelPlan.domain.place.repository.PlaceRepository;
import com.travelPlan.domain.schedule.dto.CheckResponseDto;
import com.travelPlan.domain.schedule.dto.DetailRequestDto;
import com.travelPlan.domain.schedule.entity.Schedule;
import com.travelPlan.domain.schedule.entity.ScheduleDetail;
import com.travelPlan.domain.schedule.repository.DetailRepository;
import com.travelPlan.domain.schedule.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleDetailService {
    private final DetailRepository detailRepository;
    private final ScheduleRepository scheduleRepository;
    private final PlaceRepository placeRepository;

    @Transactional
    public List<Long> saveDetails(List<DetailRequestDto> requestDtoList) {
        List<ScheduleDetail> details = new ArrayList<>();

        for(DetailRequestDto dto : requestDtoList) {
            Place place = placeRepository.findById(dto.getPlaceId())
                    .orElseThrow(() -> new IllegalArgumentException("장소를 찾을 수 없습니다. placeId = " + dto.getPlaceId()));

            Schedule schedule = scheduleRepository.findById(dto.getScheduleId())
                    .orElseThrow(() -> new IllegalArgumentException("메인 일정을 찾을 수 없습니다."));

            validateOperatingTime(place, dto.getTravelDate(), dto.getVisitTime());

            ScheduleDetail detail = ScheduleDetail.builder()
                    .schedule(schedule)
                    .place(place)
                    .travelDate(dto.getTravelDate())
                    .visitOrder(dto.getVisitOrder())
                    .visitTime(dto.getVisitTime())
                    .build();

            details.add(detail);
        }

        List<ScheduleDetail> saveDetails = detailRepository.saveAll(details);

        return saveDetails.stream()
                .map(ScheduleDetail::getDetailId)
                .toList();
    }

    public CheckResponseDto checkPossible(Long placeId, LocalDate travelDate, LocalTime visitTime) {
        Place place = placeRepository.findById(placeId)
                .orElseThrow(() -> new IllegalArgumentException("장소를 찾을 수 없습니다."));

        try{
            validateOperatingTime(place, travelDate, visitTime);
            return CheckResponseDto.builder()
                    .isPossible(true)
                    .message("영업 시간 내 방문이 가능합니다.")
                    .build();

        } catch(IllegalArgumentException e) {
            List<String> recommendationList = place.getOperatingTimes().stream()
                    .filter(op -> !op.isClosed())
                    .map(op -> op.getDayOfWeek() + ":" + op.getOpenTime() + "~" + op.getCloseTime())
                    .toList();

            return CheckResponseDto.builder()
                    .isPossible(false)
                    .message(e.getMessage())
                    .recommendations(recommendationList)
                    .build();
        }
    }

    public void validateOperatingTime(Place place, LocalDate travelDate, LocalTime visitTime) {
        OperatingTime opTime = place.getOperatingTimes().stream()
                .filter(op -> op.getDayOfWeek() == travelDate.getDayOfWeek())
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("해당 요일의 영업 정보가 없습니다."));

        if(opTime.isClosed()) {
            throw new IllegalArgumentException("선택하신 날짜는 해당 장소의 정기적인 휴무일입니다.");
        }

        if(visitTime.isBefore(opTime.getOpenTime()) || visitTime.isAfter(opTime.getCloseTime())) {
            throw new IllegalArgumentException(
                    String.format("%s의 영업시간(%s - %s) 외 방문입니다.",
                            place.getPlaceName(), opTime.getOpenTime(), opTime.getCloseTime())
            );
        }

        if(opTime.getBreakStart() != null && opTime.getBreakEnd() != null) {
            if(visitTime.isAfter(opTime.getBreakStart()) && visitTime.isBefore(opTime.getBreakEnd())) {
                throw new IllegalArgumentException(
                        String.format("%s의 브레이크 타임(%s - %s)입니다.",
                                place.getPlaceName(), opTime.getBreakStart(), opTime.getBreakEnd())
                );

            }
        }
    }
}
