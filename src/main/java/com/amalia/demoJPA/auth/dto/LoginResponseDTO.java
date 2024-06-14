package com.amalia.demoJPA.auth.dto;

import lombok.Data;

@Data
public class LoginResponseDTO {
    private String message;
    private String token;
}
