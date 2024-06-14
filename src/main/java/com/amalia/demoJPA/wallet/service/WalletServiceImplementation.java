package com.amalia.demoJPA.wallet.service;

import com.amalia.demoJPA.ExceptionHandling.DataNotFoundException;
import com.amalia.demoJPA.currencies.entity.Currencies;
import com.amalia.demoJPA.currencies.repository.CurrenciesRepository;
import com.amalia.demoJPA.users.entity.Users;
import com.amalia.demoJPA.users.repository.UserRepository;
import com.amalia.demoJPA.wallet.dto.CurrencyDTO;
import com.amalia.demoJPA.wallet.dto.UserDTO;
import com.amalia.demoJPA.wallet.dto.WalletDTO;
import com.amalia.demoJPA.wallet.entity.Wallet;
import com.amalia.demoJPA.wallet.repository.WalletRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class WalletServiceImplementation implements WalletService {

    private final WalletRepository walletRepository;
    private final UserRepository usersRepository;
    private final CurrenciesRepository currenciesRepository;

    @Autowired
    public WalletServiceImplementation(WalletRepository walletRepository, UserRepository usersRepository, CurrenciesRepository currenciesRepository) {
        this.walletRepository = walletRepository;
        this.usersRepository = usersRepository;
        this.currenciesRepository = currenciesRepository;
    }

    @Override
    public List<WalletDTO> getAllWallet() {
        return walletRepository.findAllWallets();
    }

    @Override
    public WalletDTO createWallet(Wallet wallet) {
        Users user = usersRepository.findById(wallet.getUsers().getId())
                .orElseThrow(() -> new DataNotFoundException("User not found"));
        Currencies currency = currenciesRepository.findById(wallet.getCurrencies().getId())
                .orElseThrow(() -> new DataNotFoundException("Currency not found"));

        wallet.setUsers(user);
        wallet.setCurrencies(currency);

        Wallet savedWallet = walletRepository.save(wallet);
        return new WalletDTO(
                savedWallet.getId(),
                savedWallet.getWalletName(),
                savedWallet.getAmount(),
                savedWallet.isMain(),
                savedWallet.getCreateAt(),
                savedWallet.getUpdateAt(),
                new UserDTO(savedWallet.getUsers().getId(), savedWallet.getUsers().getUsername()),
                new CurrencyDTO(savedWallet.getCurrencies().getId(), savedWallet.getCurrencies().getCurrencyName())
        );
    }


    @Override
    public WalletDTO updateWallet(Wallet wallet) {
        if (!walletRepository.existsById(wallet.getId())) {
            throw new DataNotFoundException("Wallet with id " + wallet.getId() + " not found");
        }

        Users user = usersRepository.findById(wallet.getUsers().getId())
                .orElseThrow(() -> new DataNotFoundException("User not found"));
        Currencies currency = currenciesRepository.findById(wallet.getCurrencies().getId())
                .orElseThrow(() -> new DataNotFoundException("Currency not found"));

        wallet.setUsers(user);
        wallet.setCurrencies(currency);

        wallet.setUpdateAt(Instant.now());

        Wallet updatedWallet = walletRepository.save(wallet);

        return new WalletDTO(
                updatedWallet.getId(),
                updatedWallet.getWalletName(),
                updatedWallet.getAmount(),
                updatedWallet.isMain(),
                updatedWallet.getCreateAt(),
                updatedWallet.getUpdateAt(),
                new UserDTO(updatedWallet.getUsers().getId(), updatedWallet.getUsers().getUsername()),
                new CurrencyDTO(updatedWallet.getCurrencies().getId(), updatedWallet.getCurrencies().getCurrencyName())
        );
    }

    @Transactional
    @Override
    public void setMainWallet(Long userId, Long walletId) {
        walletRepository.unsetMainWallets(userId);
        walletRepository.setMainWallet(walletId, userId);
    }

    @Transactional
    @Override
    public void softDeleteWallet(Long id) {
        if (!walletRepository.existsById(id)) {
            throw new DataNotFoundException("Wallet with id " + id + " not found");
        }
        walletRepository.softDeleteById(id);
    }
}
