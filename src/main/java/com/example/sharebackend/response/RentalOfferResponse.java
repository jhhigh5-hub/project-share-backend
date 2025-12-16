package com.example.sharebackend.response;

import com.example.sharebackend.domain.Car;
import com.example.sharebackend.domain.RentalOffer;
import com.example.sharebackend.domain.RentalOfferAddReview;
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
    List<RentalOfferAddReview> rentalOfferAddReview;

}
