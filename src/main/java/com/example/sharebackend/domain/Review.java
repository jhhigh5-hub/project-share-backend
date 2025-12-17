package com.example.sharebackend.domain;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class Review {
    int reservationIdx;

    @Size(max = 300)
    String content;

    @Max(5)
    int starRating;

    List<Review> reviewList;
}
