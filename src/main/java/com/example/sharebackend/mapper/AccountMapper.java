package com.example.sharebackend.mapper;

import com.example.sharebackend.dto.Account;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AccountMapper {
    //회원 가입
    int insertOne (Account account);


}
