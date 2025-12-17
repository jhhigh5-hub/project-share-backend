package com.example.sharebackend.controller;

import com.example.sharebackend.domain.Reservation;
import com.example.sharebackend.domain.Review;
import com.example.sharebackend.mapper.ReservationMapper;
import com.example.sharebackend.mapper.ReviewMapper;
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


    @PostMapping("reservation/{reservationIdx}/review")
    public ReviewResponse reviewPostHandle(@PathVariable int reservationIdx,
                                           @RequestBody ReviewRequest reviewRequest) {

        System.out.println(reviewRequest.getContent());
        System.out.println(reviewRequest.getReservationIdx());
        System.out.println(reviewRequest.getStarRating());

        Review newReview = new Review();
        newReview.setReservationIdx(reservationIdx);
        newReview.setContent(reviewRequest.getContent());
        newReview.setStarRating(reviewRequest.getStarRating());

        reviewMapper.insertOne(newReview);
        return ReviewResponse.builder().success(true).review(newReview).build();
    }

    @GetMapping("/review/{reservationIdx}")
    public ReviewResponse reviewAllHandle(@PathVariable int reservationIdx) {

        List<Review> reviewList = reviewMapper.selectByReservationIdx(reservationIdx);

        return ReviewResponse.builder().success(true).reviewList(reviewList).build();
    }
}
