package com.example.sharebackend.controller;

import com.example.sharebackend.domain.Car;
import com.example.sharebackend.mapper.CarMapper;
import com.example.sharebackend.request.CarAddRequest;
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
    public CarResponse addCar(@RequestBody CarAddRequest carAddRequest) {
        Car car = new Car();
        car.setCorporation(carAddRequest.getCorporation());
        car.setModelName(carAddRequest.getModelName());
        car.setCarType(carAddRequest.getCarType());
        car.setModelYear(carAddRequest.getModelYear());
        car.setFewSeats(carAddRequest.getFewSeats());
        car.setGearType(carAddRequest.getGearType());
        carMapper.insertCar(car);
        return CarResponse.builder().success(true).car(car).build();
    }

    @GetMapping("/car")
    public CarListResponse CarInfoHandle() {
        List<Car> carList = carMapper.findAllCars();
        return  CarListResponse.builder().success(true).carList(carList).build();
    }
}
