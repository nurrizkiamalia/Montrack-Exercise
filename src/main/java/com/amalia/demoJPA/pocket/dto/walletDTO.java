package com.amalia.demoJPA.pocket.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Data
public class walletDTO {
    private Long id;
    private String walletName;
    private int amount;

    public walletDTO(Long id, String walletName, int amount){
        this.id = id;
        this.walletName = walletName;
        this.amount = amount;
    }
}
