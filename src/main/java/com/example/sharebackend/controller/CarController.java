package com.example.sharebackend.controller;

import com.example.sharebackend.domain.Car;
import com.example.sharebackend.mapper.CarMapper;
import com.example.sharebackend.request.CarAddRequest;
import com.example.sharebackend.response.CarChartResponse;
import com.example.sharebackend.response.CarChartResultResponse;
import com.example.sharebackend.response.CarListResponse;
import com.example.sharebackend.response.CarAddResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequiredArgsConstructor
public class CarController {
    final CarMapper carMapper;

    // 차등록
    @PostMapping("/car")
    public CarAddResponse addCar(@RequestBody CarAddRequest carAddRequest) {
        Car car = new Car();
        car.setCorporation(carAddRequest.getCorporation());
        car.setModelName(carAddRequest.getModelName());
        car.setCarType(carAddRequest.getCarType());
        car.setModelYear(carAddRequest.getModelYear());
        car.setFewSeats(carAddRequest.getFewSeats());
        car.setGearType(carAddRequest.getGearType());
        carMapper.insertCar(car);
        return CarAddResponse.builder().success(true).car(car).build();
    }

    // 차 검색 매물 및 갯수
    @GetMapping("/car")
    public CarListResponse carInfoHandle(@RequestParam(defaultValue = "") String query) {

        List<Car> carList = carMapper.findAllCars(query);
        int allCars = carMapper.countAllCars(query);
        return CarListResponse.builder().success(true).carList(carList).total(allCars).build();
    }

    // 차트용 데이터
    @GetMapping("/car/chart")
    public CarChartResponse chartInfoHandle(){

        // 차트용
        List<CarChartResultResponse> carChart = carMapper.ChartList();

        if(carChart == null){
            return CarChartResponse.builder().success(false).message("정보가 없습니다.").build();
        }

        for(CarChartResultResponse carChartResponse : carChart){
            Car car = carMapper.findCarByIdx(carChartResponse.getIdx());
            if(car == null) continue;
            System.out.println(car.getIdx());
            CarChartResultResponse result = new CarChartResultResponse();
            result.setCorporation(car.getCorporation());
            result.setModelName(car.getModelName());
            result.setCount(car.getIdx());

        }

    return CarChartResponse.builder().success(true).build();
    }
}
