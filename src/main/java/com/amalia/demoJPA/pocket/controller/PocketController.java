package com.amalia.demoJPA.pocket.controller;

import com.amalia.demoJPA.currencies.entity.Currencies;
import com.amalia.demoJPA.pocket.dto.PocketCreateRequest;
import com.amalia.demoJPA.pocket.dto.PocketDTO;
import com.amalia.demoJPA.pocket.entity.Pockets;
import com.amalia.demoJPA.pocket.service.PocketService;
import com.amalia.demoJPA.users.entity.Users;
import com.amalia.demoJPA.wallet.dto.WalletCreateRequest;
import com.amalia.demoJPA.wallet.dto.WalletDTO;
import com.amalia.demoJPA.wallet.entity.Wallet;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("api/v1/pocket")
public class PocketController {
    private final PocketService pocketService;
    public PocketController(PocketService pocketService) {
        this.pocketService = pocketService;
    }

    @GetMapping
    public ResponseEntity<List<PocketDTO>> retrieveAllData(){
        return ResponseEntity.ok(pocketService.retrieveAllData());
    }

    @PostMapping
    public ResponseEntity<PocketDTO> createWallet(@Validated @RequestBody PocketCreateRequest request) {
        Pockets pockets = new Pockets();
        pockets.setPocketName(request.getPocketName());
        pockets.setAmount(request.getAmount());
        pockets.setDescription(request.getDescription());
        pockets.setEmoji(request.getEmoji());
        pockets.setCreateAt(Instant.now());
        pockets.setUpdateAt(Instant.now());

        Wallet wallet = new Wallet();
        wallet.setId(request.getWalletId());
        pockets.setWallet(wallet);

        PocketDTO pocketDTO = pocketService.createPocket(pockets);
        return ResponseEntity.ok(pocketDTO);
    }
}
