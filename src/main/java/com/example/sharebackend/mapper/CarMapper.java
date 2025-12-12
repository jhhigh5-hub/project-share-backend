package com.example.sharebackend.mapper;

import com.example.sharebackend.domain.Car;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CarMapper {
    int insertCar (Car car);
}
