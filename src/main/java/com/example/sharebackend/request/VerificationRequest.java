package com.example.sharebackend.request;

import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class VerificationRequest {
    String accountId;
    @Pattern(regexp = "(?=.*[A-Z])(?=.*[a-z])(?=.*\\d).{6}")
    String code;
}
