package com.example.sharebackend.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class RentalOfferAddReviewResponse {
    // rental_offer
    int idx;
    String accountId;
    int rentalPrice;
    String description;

    // car
    String corporation;
    String modelName;
    String carType;
    LocalDate modelYear;
    int fewSeats;
    String gearType;
}
