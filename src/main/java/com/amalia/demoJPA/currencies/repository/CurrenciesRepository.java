package com.amalia.demoJPA.currencies.repository;

import com.amalia.demoJPA.currencies.entity.Currencies;
import com.amalia.demoJPA.users.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrenciesRepository extends JpaRepository<Currencies, Integer> {
        Currencies findFirstByOrderByIdAsc();
}
