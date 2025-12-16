package com.example.sharebackend.response;

import com.example.sharebackend.request.RentalOfferAddReviewRequest;
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
    List<RentalOfferAddReviewRequest> rentalOfferAddReview;

}
