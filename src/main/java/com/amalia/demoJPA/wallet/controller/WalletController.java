package com.amalia.demoJPA.wallet.controller;

import com.amalia.demoJPA.currencies.entity.Currencies;
import com.amalia.demoJPA.users.entity.Users;
import com.amalia.demoJPA.wallet.dto.WalletCreateRequest;
import com.amalia.demoJPA.wallet.entity.Wallet;
import com.amalia.demoJPA.wallet.dto.WalletDTO;
import com.amalia.demoJPA.wallet.service.WalletService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("api/v1/wallet")
public class WalletController {
    private final WalletService walletService;

    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    @GetMapping
    public ResponseEntity<List<WalletDTO>> getAllWallets() {
        return ResponseEntity.ok(walletService.getAllWallet());
    }

    @PostMapping
    public ResponseEntity<WalletDTO> createWallet(@Validated @RequestBody WalletCreateRequest request) {
        Wallet wallet = new Wallet();
        wallet.setWalletName(request.getWalletName());
        wallet.setAmount(request.getAmount());
        wallet.setMain(request.isMain());
        wallet.setCreateAt(Instant.now());
        wallet.setUpdateAt(Instant.now());

        // Fetch the related user and currency entities
        Users user = new Users();
        user.setId(request.getUserId());
        wallet.setUsers(user);

        Currencies currency = new Currencies();
        currency.setId(request.getCurrencyId());
        wallet.setCurrencies(currency);

        WalletDTO walletDTO = walletService.createWallet(wallet);
        return ResponseEntity.ok(walletDTO);
    }


    @PutMapping("/{walletId}/set-main/{userId}")
    public ResponseEntity<String> setMainWallet(@PathVariable Long walletId, @PathVariable Long userId) {
        walletService.setMainWallet(userId, walletId);
        return ResponseEntity.ok("Main wallet set successfully");
    }

    @DeleteMapping("/{id}")
    public void deleteWallet(@PathVariable Long id) {
        walletService.softDeleteWallet(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<WalletDTO> updateWallet(@Validated @RequestBody WalletCreateRequest request, @PathVariable Long id) {
        Wallet wallet = new Wallet();
        wallet.setId(id);
        wallet.setWalletName(request.getWalletName());
        wallet.setAmount(request.getAmount());
        wallet.setMain(request.isMain());
        wallet.setUpdateAt(Instant.now());

        Users user = new Users();
        user.setId(request.getUserId());
        wallet.setUsers(user);

        Currencies currency = new Currencies();
        currency.setId(request.getCurrencyId());
        wallet.setCurrencies(currency);

        WalletDTO walletDTO = walletService.updateWallet(wallet);
        return ResponseEntity.ok(walletDTO);
    }
}
