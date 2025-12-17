package com.example.sharebackend.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RentalOfferReviewListResponse {
    int idx;
    String nickname;
    String content;
    int starRating;
}
