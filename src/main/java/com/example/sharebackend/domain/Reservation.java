package com.example.sharebackend.domain;

import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Setter
@Getter
public class Reservation {
    int idx;
    String accountId;
    int rentalOfferIdx;
    LocalDate startDate;
    LocalDate endDate;
    boolean reservationStatus;
    int paymentAmount;
    RentalOffer rentalOffer;

}
