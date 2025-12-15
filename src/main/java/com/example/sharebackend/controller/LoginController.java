package com.example.sharebackend.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.sharebackend.dto.Account;
import com.example.sharebackend.mapper.AccountMapper;
import com.example.sharebackend.request.LoginRequest;
import com.example.sharebackend.response.LoginResponse;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequiredArgsConstructor
@CrossOrigin
public class LoginController {
    final AccountMapper accountMapper;

    @PostMapping("/login")
    public LoginResponse loginHandle(@Valid @RequestBody LoginRequest lrt, HttpSession session){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        Account account = accountMapper.selectAll(lrt.getId());

        if(account != null && passwordEncoder.matches(lrt.getPw(), account.getPw())){
            String token = JWT.create().withSubject(String.valueOf(account.getId()))
                    .withIssuedAt(new Date()).withIssuer("carshare")
                    .sign(Algorithm.HMAC256("secretKey"));

            Cookie cookie = new Cookie("Authorization", token);
            cookie.setPath("/");
            cookie.setHttpOnly(true);

            return  LoginResponse.builder().success(true).account(account).token(token).build();

        }
        return LoginResponse.builder().success(false).account(null).token(null).build();

    }

}
