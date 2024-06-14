package com.amalia.demoJPA.pocket.dto;

import com.amalia.demoJPA.wallet.dto.WalletDTO;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Setter
@Getter
@Data
public class PocketDTO {
    private int id;
    private String pocketName;
    private int amount;
    private String description;
    private String emoji;
    private Instant createAt;
    private Instant updateAt;
    private walletDTO wallet;

    public PocketDTO(int id, String pocketName, int amount, String description, String emoji, Instant createAt, Instant updateAt, walletDTO wallet){
        this.id = id;
        this.pocketName = pocketName;
        this.description = description;
        this.emoji = emoji;
        this.createAt = createAt;
        this.updateAt = updateAt;
        this.wallet = wallet;
    }
}
