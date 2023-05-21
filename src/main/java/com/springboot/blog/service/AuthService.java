package com.springboot.blog.service;

import com.springboot.blog.payload.LoginDto;
import com.springboot.blog.payload.RegisterDto;

import java.util.List;

public interface AuthService {
    List<String> login(LoginDto loginDto);

    String register(RegisterDto registerDto);
}
