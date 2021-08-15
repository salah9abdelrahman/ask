package com.salah.ask.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Setter
@Getter
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class LoginRequest {

    public LoginRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    @NotNull(message = "email is required")
    @NotEmpty(message = "email is required")
    private String email;

    @NotNull(message = "password is required")
    @NotEmpty(message = "password is required")
    private String password;
}
