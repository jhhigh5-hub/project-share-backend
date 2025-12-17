package com.example.sharebackend.response;

import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RentalOfferDayListResponse {
    int rentalOfferIdx;
    String nickname;
    int carIdx;
    int rentalPrice;
    String description;
    Integer carImgIdx;
    Integer carImgRentalOfferIdx;
    String imgUrl;
}
