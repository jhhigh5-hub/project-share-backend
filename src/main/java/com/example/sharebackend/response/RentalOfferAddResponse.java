package com.example.sharebackend.response;

import com.example.sharebackend.domain.CarImg;
import com.example.sharebackend.domain.RentalOffer;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Builder
public class RentalOfferAddResponse {
    boolean success;
    String message;
    int registeredRentalOfferIdx;
    RentalOffer rentalOffer;
    List<CarImg> carImg;
}
