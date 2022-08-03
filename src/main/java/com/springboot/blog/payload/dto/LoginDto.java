package com.springboot.blog.payload.dto;


import lombok.Data;

@Data
public class LoginDto {
    private String usernameOrEmail;
    private String password;
}
