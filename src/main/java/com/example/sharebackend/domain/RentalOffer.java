package com.example.sharebackend.domain;

import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

@Setter
@Getter
public class RentalOffer {
    int idx;
    String accountId;
    int carIdx;
    int rentalPrice;
    String description;
    Car car;
}
