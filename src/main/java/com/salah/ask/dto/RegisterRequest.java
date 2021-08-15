package com.salah.ask.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Setter
@Getter
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class RegisterRequest {
    @NotBlank
    @Email(message = "wrong email format")
    private String email;

    @NotBlank
    private String password;

    @NotBlank(message = "First name is required")
    private String firstName;

    @NotBlank
    private String lastName;
}
