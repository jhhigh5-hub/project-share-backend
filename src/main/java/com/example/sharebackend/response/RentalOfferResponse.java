package com.example.sharebackend.response;

import com.example.sharebackend.domain.CarImg;
import lombok.*;

import java.util.List;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RentalOfferResponse {
    int idx;
    String accountId;
    int carIdx;
    int rentalPrice;
    String description;

    List<CarImg> carImages;
}
