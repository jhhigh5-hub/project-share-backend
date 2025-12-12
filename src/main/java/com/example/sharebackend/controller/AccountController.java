package com.example.sharebackend.controller;


import com.example.sharebackend.dto.Account;
import com.example.sharebackend.dto.Verification;
import com.example.sharebackend.mapper.AccountMapper;
import com.example.sharebackend.mapper.VerificationMapper;
import com.example.sharebackend.request.AccountSignupRequest;
import com.example.sharebackend.response.AccountSignupResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@CrossOrigin
public class AccountController {
    final AccountMapper accountSignupMapper;
    final VerificationMapper verificationMapper;

    @PostMapping("/signup")
    public AccountSignupResponse Accountsignup(@Valid @RequestBody AccountSignupRequest asr,
                                               BindingResult result) {
        if(result.hasErrors()) {
            AccountSignupResponse accountSignupResponse = new AccountSignupResponse();
            System.out.println("id ?" + result.hasFieldErrors("id"));
            System.out.println("nickanme ?" + result.hasFieldErrors("nickname"));
            System.out.println("pw ?" + result.hasFieldErrors("pw"));
            accountSignupResponse.setSuccess(false);
            return accountSignupResponse;
        }

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        Account account = asr.toAccount(passwordEncoder.encode(asr.getPw()));
        accountSignupMapper.insertOne(account);
        Verification verification = new Verification();
        if

        return null;
    }
}
