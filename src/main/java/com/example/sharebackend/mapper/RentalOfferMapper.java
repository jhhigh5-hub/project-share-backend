package com.example.sharebackend.mapper;

import com.example.sharebackend.domain.RentalOffer;
import com.example.sharebackend.response.RentalOfferResponse;
import org.apache.ibatis.annotations.Mapper;

import com.example.sharebackend.domain.CarImg;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDate;
import java.util.List;


@Mapper
public interface RentalOfferMapper {
    int selectByRentalPrice(int rentalPrice);

    int insertRentalOffer(RentalOffer rentalOffer);

    int insertCarImg(CarImg carImg);

    List<CarImg> findCarImgs(int rentalOfferIdx);

//    List<CarImg> findAllCarImgs();
//
//    List<RentalOffer> findAllRentalOffer();

    int countAllRentalOffer();

    // findAvailableRentalOffers 메서드 시그니처 수정!
    List<RentalOfferResponse> findAvailableRentalOffers(@Param("desiredStartDate") LocalDate desiredStartDate,
                                                        @Param("desiredEndDate") LocalDate desiredEndDate
    );

    List<RentalOfferResponse> findAllRentalOffersWithImages();
}
