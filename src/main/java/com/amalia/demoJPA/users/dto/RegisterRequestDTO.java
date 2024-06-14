package com.amalia.demoJPA.users.dto;

import com.amalia.demoJPA.currencies.entity.Currencies;
import com.amalia.demoJPA.languages.entity.Languages;
import com.amalia.demoJPA.users.entity.Users;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RegisterRequestDTO {
    @NotBlank(message = "Name is required")
    private String username;

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;

    @NotBlank(message = "Password is required")
    private String password;

    @NotBlank(message = "Password confirmation is required")
    private String passwordConfirmation;

    @NotNull
    @Min(1)
    private int activeCurrency;

    public Users toEntity() {
        Users user = new Users();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);
        Languages languagesData = new Languages();
        languagesData.setId(activeCurrency);
        user.setLanguages(languagesData);

        Currencies currency = new Currencies();
        currency.setId(activeCurrency);
        user.setCurrencies(currency);

        return user;
    }

}
