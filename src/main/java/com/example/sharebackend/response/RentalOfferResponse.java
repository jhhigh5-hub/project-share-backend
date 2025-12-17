package com.example.sharebackend.response;

import com.example.sharebackend.domain.*;
import lombok.*;

import java.util.List;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RentalOfferResponse {
    boolean success;
    String message;
    List<Review> reviewList;
    List<RentalOfferAddReviewResponse> rentalOfferAddReview;
    List<CarImg> rentalOfferCarImg;
    List<RentalOfferReviewResponse> rentalOfferReview;
}
