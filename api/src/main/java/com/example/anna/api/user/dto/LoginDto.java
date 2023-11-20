package com.example.anna.api.user.dto;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class LoginDto {

    @NotBlank
    private String userId;

    @NotBlank
    private String password;

    private String err;

    public LoginDto() {
    }

    public LoginDto(@NotBlank String userId, @NotBlank String password) {
        this.userId = userId;
        this.password = password;
    }
}
