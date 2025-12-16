package com.example.sharebackend.mapper;

import com.example.sharebackend.domain.RentalOffer;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RentalOfferMapper {
    // 하루 렌트비 조회
    int selectByRentalPrice (int rentalPrice);
import com.example.sharebackend.domain.Car;
import com.example.sharebackend.domain.CarImg;
import com.example.sharebackend.domain.RentalOffer;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface RentalOfferMapper {
    int insertRentalOffer(RentalOffer rentalOffer);
    int insertCarImg(CarImg carImg);

    List<CarImg> findCarImgs(int rentalOfferIdx);

    // findAvailableRentalOffers 메서드 시그니처 수정!
    List<RentalOffer> findAvailableRentalOffers(@Param("desiredStartDate") LocalDate desiredStartDate,
                                                @Param("desiredEndDate") LocalDate desiredEndDate
    );
}
