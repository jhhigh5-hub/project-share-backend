package com.example.sharebackend.mapper;

import com.example.sharebackend.domain.CarImg;
import com.example.sharebackend.domain.RentalOffer;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RentalOfferMapper {
    int insertRentalOffer(RentalOffer rentalOffer);
    int insertCarImg(CarImg carImg);
    List<RentalOffer> findAvailableRentalOffers();
}
