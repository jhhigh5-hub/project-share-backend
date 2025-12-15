package com.example.sharebackend.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter @Getter
public class Car {
    int idx;
    String corporation;
    String modelName;
    String carType;
    int modelYear;
    int fewSeats;
    String gearType;
}
