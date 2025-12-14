package com.example.sharebackend.controller;

import com.example.sharebackend.dto.Account;
import com.example.sharebackend.dto.Verify;
import com.example.sharebackend.mapper.AccountMapper;
import com.example.sharebackend.mapper.VerifyMapper;
import com.example.sharebackend.request.VerifyRequest;
import com.example.sharebackend.response.VerifyResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.SecureRandom;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@CrossOrigin
public class VerifyController {
    final AccountMapper accountMapper;
    final VerifyMapper verifyMapper;


    @PostMapping("verify-email")
    public VerifyResponse VerifyEmail(@Valid @RequestBody VerifyRequest vrt,
                                      BindingResult result,
                                      @SessionAttribute ("accountId") String accountId) {
        VerifyResponse verifyResponse = new VerifyResponse();
        if(result.hasErrors()){
            System.out.println("code ? " + result.hasFieldErrors("code"));
            return verifyResponse;
        }

        Verify verify = verifyMapper.selectByAccountId(accountId);

        if(verify.getCode().equals(vrt.getCode()) && accountId.equals(verify.getAccountId())) {
            int c = accountMapper.activeUpdate(accountId);
            System.out.println("인증 성공 " + c);

            return VerifyResponse.builder().success(true).build();
        }

        return VerifyResponse.builder().success(false).build();

    }
}
