package com.amalia.demoJPA.wallet.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CurrencyDTO {
    private int id;
    private String currencyName;

    public CurrencyDTO(int id, String currencyName) {
        this.id = id;
        this.currencyName = currencyName;
    }
}
