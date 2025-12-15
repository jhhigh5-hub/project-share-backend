package com.example.sharebackend.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Verify {
    String accountId;
    String code;
}
