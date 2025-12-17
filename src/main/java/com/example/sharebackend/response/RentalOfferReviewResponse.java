package com.example.sharebackend.response;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RentalOfferReviewResponse {
    String nickname;
    int idx;
    int reservationIdx;
    String content;
    int starRating;
}
