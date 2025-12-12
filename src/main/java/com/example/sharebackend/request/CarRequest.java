package com.example.sharebackend.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class CarRequest {
    int idx;
    String corporation;
    String modelName;
    String carType;
    LocalDate modelYear;
    int fewSeats;
    String gearType;
}

