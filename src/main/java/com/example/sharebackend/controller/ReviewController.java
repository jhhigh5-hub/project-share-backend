package com.example.sharebackend.controller;

import com.example.sharebackend.domain.Reservation;
import com.example.sharebackend.domain.Review;
import com.example.sharebackend.mapper.ReservationMapper;
import com.example.sharebackend.mapper.ReviewMapper;
import com.example.sharebackend.request.ReservationRequest;
import com.example.sharebackend.request.ReviewRequest;
import com.example.sharebackend.response.ReviewResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@CrossOrigin
public class ReviewController {
    final ReviewMapper reviewMapper;
    final ReservationMapper reservationMapper;

    @PostMapping("reservation/{reservationIdx}/review")
    public ReviewResponse reviewPostHandle(@PathVariable int reservationIdx,
                                           @RequestAttribute String currentAccountId,
                                           @RequestBody ReviewRequest reviewRequest) {

        Reservation reservation = reservationMapper.selectById(reservationIdx);
        if (reservation == null) {
            return ReviewResponse.builder().success(false)
                    .message("존재하지 않는 예약입니다.").build();
        }

        if(!reservation.getAccountId().equals(currentAccountId)) {
            return ReviewResponse.builder().success(false)
                    .message("본인의 예약만 리뷰 작성 가능합니다.").build();
        }

        List<Review> reviewList = reviewMapper.selectByReservationIdxList(reservationIdx);
        if (reviewList != null && !reviewList.isEmpty()) {
            return ReviewResponse.builder().success(false)
                    .message("이미 작성된 리뷰가 있습니다.").build();
        }

        Review review = new Review();
        review.setReservationIdx(reservationIdx);
        review.setContent(reviewRequest.getContent());
        review.setStarRating(reviewRequest.getStarRating());

        reviewMapper.insertOne(review);

        return ReviewResponse.builder().success(true).message("작성 성공").review(review).build();
    }

    @GetMapping("/review/{reservationIdx}")
    public ReviewResponse reviewAllHandle(@PathVariable int reservationIdx) {
        List<Review> reviewList = reviewMapper.selectByReservationIdxList(reservationIdx);
        return ReviewResponse.builder().success(true).reviewList(reviewList).build();
    }
}
