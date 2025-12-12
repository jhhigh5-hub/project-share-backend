package com.example.sharebackend.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter @Getter
public class Car {
    int id;
    String corporation;
    String modelName;
    String carType;
    LocalDate modelYear;
    int fewSeats;
    String gearType;
}
