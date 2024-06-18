package com.swd391.bachhoasi_worker.service;

import org.springframework.stereotype.Service;

import com.swd391.bachhoasi_worker.model.dto.request.LoginDto;
import com.swd391.bachhoasi_worker.model.dto.response.LoginResponse;

@Service
public interface AuthService {
    LoginResponse login(LoginDto loginDto);
    LoginResponse createAccessToken(String refreshToken);
}
