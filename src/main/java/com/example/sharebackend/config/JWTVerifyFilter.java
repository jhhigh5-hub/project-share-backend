package com.example.sharebackend.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JWTVerifyFilter extends OncePerRequestFilter {
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String uri = request.getRequestURI(); // 요청 주소
        String method = request.getMethod();
        if(uri.startsWith("/api/account") || uri.startsWith("/api/test") || method.equals("OPTIONS")) {
            return true;
        }
        return false;
    }

    @Override // doFilterInteranl 필수
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
         String token = request.getHeader("Token");

        if (token == null) {
            response.getWriter().println("{\"success\":false}");
            return ;
        }
        DecodedJWT jwt;
        try {
            jwt = JWT.require(Algorithm.HMAC256("himedia")).withIssuer("moneyflow").build().verify(token);
        } catch (Exception e) {
            response.getWriter().println("{\"success\":false}");
            return ;
        }

        int subject = Integer.parseInt(jwt.getSubject()); // 인증시에 사용했던 계정의 ID를 설정해서 보냄
        request.setAttribute("currentAccountId", subject);
        // request.setAttribute 할때 키값은 설정하기 나름

        filterChain.doFilter(request, response);
    }
}
