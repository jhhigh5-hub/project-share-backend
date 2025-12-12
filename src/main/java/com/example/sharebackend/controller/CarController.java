package com.example.sharebackend.controller;

import com.example.sharebackend.domain.Car;
import com.example.sharebackend.mapper.CarMapper;
import com.example.sharebackend.request.CarRequest;
import com.example.sharebackend.response.CarResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@CrossOrigin
@RequiredArgsConstructor
public class CarController {
        final CarMapper carMapper;

    @GetMapping("/car")
    public CarResponse CarInfoHandle(@RequestParam int idx, @RequestParam String corporation,
                                     @RequestParam String modelName, @RequestParam String carType,
                                     @RequestParam LocalDate modelYear, @RequestParam int fewSeats,
                                     @RequestParam String gearType) {
        Car car = new Car();
        car.setIdx(idx);
        car.setCorporation(corporation);
        car.setModelName(modelName);
        car.setCarType(carType);
        car.setModelYear(modelYear);
        car.setFewSeats(fewSeats);
        car.setGearType(gearType);
        carMapper.insertCar(car);

        return CarResponse.builder().success(true).car(car).build();
    }
}
