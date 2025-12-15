package com.example.sharebackend.mapper;

import com.example.sharebackend.domain.Reservation;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface ReservationMapper {
    // 예약하기
    int insertOne(Reservation reservation);

    // 예약 리스트 조회
    List<Reservation> selectListAll(String accountId, int idx);

    // 예약 총 횟수
    int selectAllCount(String accountId);

    // 중복 예약 체크
    int existsReservation(int rentalOfferIdx, LocalDate startDate, LocalDate endDate);
}
