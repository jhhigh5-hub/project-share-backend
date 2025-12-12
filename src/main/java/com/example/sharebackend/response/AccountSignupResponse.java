package com.example.sharebackend.response;

import com.example.sharebackend.dto.Account;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AccountSignupResponse {
    boolean success;
    Account account;
}
