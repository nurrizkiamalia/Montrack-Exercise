package com.amalia.demoJPA.users.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ForgotPasswordDTO {
    @NotBlank(message = "Email is required")
    @Email
    private String email;

    private String password;
}
