package com.amalia.demoJPA.wallet.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class WalletCreateRequest {

    @NotBlank(message = "Wallet name must not be empty")
    private String walletName;

    @Min(value = 0, message = "Amount of money must be zero or positive")
    private int amount;

    @NotNull(message = "IsMain must be specified")
    private boolean isMain;

    @NotNull(message = "User ID must be specified")
    private Long userId;

    @NotNull(message = "Currency ID must be specified")
    private int currencyId;
}
