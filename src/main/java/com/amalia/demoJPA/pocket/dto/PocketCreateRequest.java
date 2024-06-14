package com.amalia.demoJPA.pocket.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PocketCreateRequest {
    @NotBlank(message = "Pocket name must not be empty")
    private String pocketName;

    @Min(value = 0, message = "Amount of money must be zero or positive")
    private int amount;

    @NotBlank(message = "Description name must not be empty")
    private String description;

    @NotBlank(message = "Emoji name must not be empty")
    private String emoji;

    @NotNull(message = "Wallet ID must be specified")
    private Long walletId;
}
