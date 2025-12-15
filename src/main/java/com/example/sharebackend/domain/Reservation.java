package com.example.sharebackend.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Setter
@Getter
public class Reservation {
    String accountId;
    int rentalOfferIdx;
    LocalDate startDate;
    LocalDate endDate;
    boolean reservationStatus;
    int paymentAmount;

}
