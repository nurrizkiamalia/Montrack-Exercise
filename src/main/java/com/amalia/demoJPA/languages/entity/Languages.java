package com.amalia.demoJPA.languages.entity;

import com.amalia.demoJPA.users.entity.Users;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

@Setter
@Getter
@Entity
@Table(name = "languages")
public class Languages {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "languages_id_gen")
    @SequenceGenerator(name = "languages_id_gen",sequenceName = "languages_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private int id;

    @NotNull
    @Column(name = "language_name", nullable = false)
    private String languageName;

    @NotNull
    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "create_at", nullable = false)
    private Instant createAt;

    @NotNull
    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "update_at", nullable = false)
    private Instant updateAt;

    @Column(name = "delete_at")
    private Instant deleteAt;

}
