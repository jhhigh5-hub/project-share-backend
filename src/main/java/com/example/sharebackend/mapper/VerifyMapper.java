package com.example.sharebackend.mapper;

import com.example.sharebackend.dto.Verify;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface VerifyMapper {
    // 코드 인증
    int insertCode (String accountId, String code);
    Verify selectByAccountId(String accountId);
}
