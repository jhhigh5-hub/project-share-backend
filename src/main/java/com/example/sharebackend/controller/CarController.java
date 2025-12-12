package com.example.sharebackend.controller;

import com.example.sharebackend.domain.Car;
import com.example.sharebackend.mapper.CarMapper;
import com.example.sharebackend.request.CarRequest;
import com.example.sharebackend.response.CarListResponse;
import com.example.sharebackend.response.CarResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequiredArgsConstructor
public class CarController {
        final CarMapper carMapper;

    @PostMapping("/car")
    public CarResponse addCar(@RequestBody CarRequest carRequest) {
        Car car = new Car();
        car.setCorporation(carRequest.getCorporation());
        car.setModelName(carRequest.getModelName());
        car.setCarType(carRequest.getCarType());
        car.setModelYear(carRequest.getModelYear());
        car.setFewSeats(carRequest.getFewSeats());
        car.setGearType(carRequest.getGearType());
        carMapper.insertCar(car);
        return CarResponse.builder().success(true).car(car).build();
    }

    @GetMapping("/car")
    public CarListResponse CarInfoHandle() {
        List<Car> carList = carMapper.findAllCars();
        return  CarListResponse.builder().success(true).carList(carList).build();
    }
}
