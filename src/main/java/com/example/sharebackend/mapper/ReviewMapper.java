package com.example.sharebackend.mapper;

import com.example.sharebackend.domain.Review;
import com.example.sharebackend.response.RentalOfferResponse;
import com.example.sharebackend.response.RentalOfferReviewListResponse;
import com.example.sharebackend.response.RentalOfferReviewResponse;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReviewMapper {
    // 리뷰 작성
    int insertOne(Review  review);

    // reservationIdx에 해당하는 모든 리뷰 조회
    List<Review> selectByReservationIdxList(int  reservationIdx);

    //  rentalOfferIdx에 해당하는 모든 리뷰 조회
    List<RentalOfferReviewListResponse> selectByRentalOfferIdx(int rentalOfferIdx);
}
