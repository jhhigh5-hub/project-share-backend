package com.example.sharebackend.mapper;

import com.example.sharebackend.domain.Review;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReviewMapper {
    int insertOne(Review review);
    List<Review> selectByReservationIdx(int  reservationIdx);
}
