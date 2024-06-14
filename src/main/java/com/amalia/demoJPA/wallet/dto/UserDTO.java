package com.amalia.demoJPA.wallet.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserDTO {
    private Long id;
    private String username;

    public UserDTO(Long id, String username) {
        this.id = id;
        this.username = username;
    }
}
