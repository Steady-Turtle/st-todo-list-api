package com.example.anna.api.user.dto;

import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
public class SignupDto {

    @NotBlank
    @Size(max = 50)
    private String userId;

    @NotBlank
    @Size(min = 6, max = 20)
    private String password;

    @Size( max = 50)
    private String email;

}
