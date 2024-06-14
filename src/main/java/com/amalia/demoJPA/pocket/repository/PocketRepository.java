package com.amalia.demoJPA.pocket.repository;

import com.amalia.demoJPA.pocket.dto.PocketDTO;
import com.amalia.demoJPA.pocket.entity.Pockets;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PocketRepository extends JpaRepository<Pockets, Integer> {
    @Query("SELECT new com.amalia.demoJPA.pocket.dto.PocketDTO(p.id, p.pocketName, p.amount, p.description, p.emoji, p.createAt, p.updateAt, " +
            "new com.amalia.demoJPA.pocket.dto.walletDTO(w.id, w.walletName, w.amount)) " +
            "FROM Pockets p JOIN p.wallet w")
    List<PocketDTO> retrieveAllPocket();
}
