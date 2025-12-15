package com.example.sharebackend.mapper;

import com.example.sharebackend.domain.RentalOffer;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RentalOfferMapper {
    // 하루 렌트비 조회
    int selectByRentalPrice (int rentalPrice);
}
