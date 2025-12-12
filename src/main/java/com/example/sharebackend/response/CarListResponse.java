package com.example.sharebackend.response;

import com.example.sharebackend.domain.Car;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Setter
@Getter
@Builder
public class CarListResponse {
    boolean success;
    List<Car> carList;
    int countAllCar;
}
