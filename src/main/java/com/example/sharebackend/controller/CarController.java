package com.example.sharebackend.controller;

import com.example.sharebackend.domain.Car;
import com.example.sharebackend.mapper.CarMapper;
import com.example.sharebackend.request.CarRequest;
import com.example.sharebackend.response.CarResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequiredArgsConstructor
public class CarController {
        final CarMapper carMapper;

    @GetMapping("/car")
    public CarResponse CarInfoHandle(@RequestBody CarRequest carRequest) {
        Car car = new Car();
        car.setIdx(carRequest.getIdx());
        car.setCorporation(carRequest.getCorporation());
        car.setModelName(carRequest.getModelName());
        car.setCarType(carRequest.getCarType());
        car.setModelYear(carRequest.getModelYear());
        car.setFewSeats(carRequest.getFewSeats());
        car.setGearType(carRequest.getGearType());
        carMapper.insertCar(car);

        return CarResponse.builder().success(true).car(car).build();
    }
}
