package com.swd391.bachhoasi_worker.service.impl;

import lombok.RequiredArgsConstructor;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.swd391.bachhoasi_worker.model.constant.TokenType;
import com.swd391.bachhoasi_worker.model.dto.request.LoginDto;
import com.swd391.bachhoasi_worker.model.dto.response.LoginResponse;
import com.swd391.bachhoasi_worker.model.entity.Admin;
import com.swd391.bachhoasi_worker.model.exception.AuthFailedException;
import com.swd391.bachhoasi_worker.repository.AdminRepository;
import com.swd391.bachhoasi_worker.security.JwtProvider;
import com.swd391.bachhoasi_worker.service.AuthService;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final AdminRepository adminRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;

    @Override
    public LoginResponse login(LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String accessToken = jwtProvider.generateToken(authentication, TokenType.ACCESS_TOKEN);
        String refreshToken = jwtProvider.generateRefreshToken(authentication);
        try {
            Admin user = adminRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName())
            .orElseThrow();
            return new LoginResponse(accessToken, refreshToken, user.getRole());
        }catch (Exception e){
            throw new AuthFailedException("Your account may have been remove or disable during login");
        }
    }

    public LoginResponse createAccessToken(String refreshToken) {
        try {
            String username = jwtProvider.getUsernameFromJWT(refreshToken, TokenType.REFRESH_TOKEN);
            String accessToken = jwtProvider.generateToken(username, TokenType.ACCESS_TOKEN);
            Admin user = adminRepository.findByUsername(username)
            .orElseThrow();
            return new LoginResponse(accessToken, refreshToken, user.getRole());
        }catch (Exception e){
            throw new AuthFailedException("Refresh token isn't valid, please try again");
        }
    }
}
