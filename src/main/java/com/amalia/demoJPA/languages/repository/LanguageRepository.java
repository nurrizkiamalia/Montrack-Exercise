package com.amalia.demoJPA.languages.repository;

import com.amalia.demoJPA.languages.entity.Languages;
import com.amalia.demoJPA.users.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LanguageRepository extends JpaRepository<Languages, Integer> {
        Languages findFirstByOrderByIdAsc();
}
