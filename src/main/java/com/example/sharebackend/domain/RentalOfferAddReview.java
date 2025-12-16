package com.example.sharebackend.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class RentalOfferAddReview {
    // rental_offer
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

    // review
    String content;
    int starRating;




}
