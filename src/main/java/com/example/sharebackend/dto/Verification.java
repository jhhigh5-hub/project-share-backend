package com.example.sharebackend.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Verification {
    int accountId;
    String code;
}
