package com.amalia.demoJPA.wallet.repository;

import com.amalia.demoJPA.wallet.dto.WalletDTO;
import com.amalia.demoJPA.wallet.entity.Wallet;
import com.amalia.demoJPA.wallet.entity.WalletProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface  WalletRepository extends JpaRepository<Wallet, Long> {

    @Query("SELECT new com.amalia.demoJPA.wallet.dto.WalletDTO(w.id, w.walletName, w.amount, w.isMain, w.createAt, w.updateAt, " +
            "new com.amalia.demoJPA.wallet.dto.UserDTO(u.id, u.username), " +
            "new com.amalia.demoJPA.wallet.dto.CurrencyDTO(c.id, c.currencyName)) " +
            "FROM Wallet w JOIN w.users u JOIN w.currencies c")
    List<WalletDTO> findAllWallets();

    @Modifying
    @Transactional
    @Query("UPDATE Wallet w SET w.deleteAt = CURRENT_TIMESTAMP WHERE w.id = :id")
    void softDeleteById(@Param("id") Long id);

    @Query("SELECT w.id AS id, w.walletName AS walletName, w.amount AS amount, w.currencies.id AS currenciesId, w.users.id AS usersId FROM Wallet w")
    List<WalletProjection> findAllWalletsProjection();

    @Modifying
    @Transactional
    @Query("UPDATE Wallet w SET w.isMain = false WHERE w.users.id = :userId")
    void unsetMainWallets(@Param("userId") Long userId);

    @Modifying
    @Transactional
    @Query("UPDATE Wallet w SET w.isMain = true WHERE w.id = :walletId AND w.users.id = :userId")
    void setMainWallet(@Param("walletId") Long walletId, @Param("userId") Long userId);
}
