package com.example.sharebackend.mapper;

import com.example.sharebackend.domain.*;
import com.example.sharebackend.response.RentalOfferAddReviewResponse;

import com.example.sharebackend.response.RentalOfferResponse;
import org.apache.ibatis.annotations.Mapper;

import com.example.sharebackend.domain.RentalOffer;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDate;
import java.util.List;


@Mapper
public interface  RentalOfferMapper {
    int selectByRentalPrice(int rentalPrice);

    int insertRentalOffer(RentalOffer rentalOffer);

    int insertCarImg(CarImg carImg);

    List<CarImg> findCarImgs(int rentalOfferIdx);

    List<RentalOffer> findAllRentalOffer();

    int countAllRentalOffer();

    // findAvailableRentalOffers 메서드 시그니처 수정!
    List<RentalOfferResponse> findAvailableRentalOffers(@Param("desiredStartDate") LocalDate desiredStartDate,
                                                        @Param("desiredEndDate") LocalDate desiredEndDate
    );


    // 특정 매물 정보 조회
    List<RentalOfferAddReviewResponse> selectRentalOfferAndReview(int rentalOfferIdx);

    // 매물 전체 조회
    List<RentalOfferAddReviewResponse> findAllRentalOffersWithImages();

    // 매물 전체 이미지 조회
    List<CarImg> rentalOfferAllImages(@Param("ids") List<Integer> ids);

}
