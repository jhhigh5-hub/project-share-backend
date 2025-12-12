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
            String uri = request.getRequestURI();   // 요청 주소
            String method = request.getMethod();

            if (uri.equals("/signup") || uri.equals("/login") || uri.equals("/verify-email") || uri.equals("/car")) {
                return true;
            } else {
                return false;
            }
        }

        @Override
        protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
            String token = request.getHeader("Token");

            if (token == null) {
                response.getWriter().println("{\"success\":false}");
                return;
            }
            DecodedJWT jwt;
            try {
                jwt = JWT.require(Algorithm.HMAC256("secretKey")).withIssuer("carshare").build().verify(token);
            } catch (Exception e) {
                response.getWriter().println("{\"success\": false}");
                return;
            }

            int subject = Integer.parseInt(jwt.getSubject());   // auth할 때 인증했던 계정의 id를 설정해서 보내뒀음.
            request.setAttribute("currentAccountId", subject);
            filterChain.doFilter(request, response);
        }



    }
