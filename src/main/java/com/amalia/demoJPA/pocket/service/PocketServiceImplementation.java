package com.amalia.demoJPA.pocket.service;

import com.amalia.demoJPA.ExceptionHandling.DataNotFoundException;
import com.amalia.demoJPA.pocket.dto.PocketDTO;
import com.amalia.demoJPA.pocket.dto.walletDTO;
import com.amalia.demoJPA.pocket.entity.Pockets;
import com.amalia.demoJPA.pocket.repository.PocketRepository;
import com.amalia.demoJPA.wallet.dto.CurrencyDTO;
import com.amalia.demoJPA.wallet.entity.Wallet;
import com.amalia.demoJPA.wallet.repository.WalletRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PocketServiceImplementation implements PocketService {
    private final PocketRepository pocketRepository;
    private final WalletRepository walletRepository;

    public PocketServiceImplementation(PocketRepository pocketRepository, WalletRepository walletRepository) {
        this.pocketRepository = pocketRepository;
        this.walletRepository = walletRepository;
    }

    @Override
    public List<PocketDTO> retrieveAllData() {
        return pocketRepository.retrieveAllPocket();
    }

    @Override
    public PocketDTO createPocket(Pockets pockets) {
        Wallet wallet = walletRepository.findById(pockets.getWallet().getId())
                .orElseThrow(() -> new DataNotFoundException("User not found"));

        pockets.setWallet(wallet);

        Pockets savedPocket = pocketRepository.save(pockets);

        return new PocketDTO(
                savedPocket.getId(),
                savedPocket.getPocketName(),
                savedPocket.getAmount(),
                savedPocket.getDescription(),
                savedPocket.getEmoji(),
                savedPocket.getCreateAt(),
                savedPocket.getUpdateAt(),
                new walletDTO(savedPocket.getWallet().getId(), savedPocket.getWallet().getWalletName(), savedPocket.getWallet().getAmount())
        );
    }
}
