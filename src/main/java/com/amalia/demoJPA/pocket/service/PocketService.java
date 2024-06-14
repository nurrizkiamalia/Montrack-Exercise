package com.amalia.demoJPA.pocket.service;

import com.amalia.demoJPA.pocket.dto.PocketDTO;
import com.amalia.demoJPA.pocket.entity.Pockets;

import java.util.List;

public interface PocketService {
    List<PocketDTO> retrieveAllData();

    PocketDTO createPocket(Pockets pockets);
}
