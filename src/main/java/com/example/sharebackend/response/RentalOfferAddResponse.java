package com.example.sharebackend.response;

import com.example.sharebackend.domain.RentalOffer;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class RentalOfferAddResponse {
    boolean success;
    String message;
    int registeredRentalOfferIdx;
    RentalOffer rentalOffer;
}
