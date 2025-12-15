package com.example.sharebackend.response;

import com.example.sharebackend.dto.Account;
import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountResponse {
    boolean success;
    Account account;
}
