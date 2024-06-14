package com.amalia.demoJPA.wallet.service;

import com.amalia.demoJPA.wallet.dto.WalletDTO;
import com.amalia.demoJPA.wallet.entity.Wallet;

import java.util.List;

public interface WalletService {
    public List<WalletDTO> getAllWallet();
    public WalletDTO createWallet(Wallet wallet);
    public WalletDTO updateWallet(Wallet wallet);
    public void setMainWallet(Long userId, Long walletId);
    void softDeleteWallet(Long id);
//    void deleteWallet(Long id);
}
