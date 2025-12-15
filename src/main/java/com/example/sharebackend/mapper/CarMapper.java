package com.example.sharebackend.mapper;

import com.example.sharebackend.domain.Car;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CarMapper {
    int insertCar (Car car);
    List<Car> findAllCars();
}
