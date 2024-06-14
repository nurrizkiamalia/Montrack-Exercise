package com.amalia.demoJPA.wallet.dto;

import lombok.Getter;
import lombok.Setter;
import java.time.Instant;
@Setter
@Getter
public class WalletDTO {
    private Long id;
    private String walletName;
    private int amount;
    private boolean isMain;
    private Instant createAt;
    private Instant updateAt;
    private UserDTO user;
    private CurrencyDTO currency;
    public WalletDTO(Long id, String walletName, int amount, boolean isMain, Instant createAt, Instant updateAt, UserDTO user, CurrencyDTO currency) {
        this.id = id;
        this.walletName = walletName;
        this.amount = amount;
        this.isMain = isMain;
        this.createAt = createAt;
        this.updateAt = updateAt;
        this.user = user;
        this.currency = currency;
    }
}
