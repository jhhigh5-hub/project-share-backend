package com.example.sharebackend.mapper;

import com.example.sharebackend.dto.Verification;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface VerificationMapper {
    // 코드 인증
    int insertCode (Verification verification);
    Verification selectByAccountId(String accountId);
}
