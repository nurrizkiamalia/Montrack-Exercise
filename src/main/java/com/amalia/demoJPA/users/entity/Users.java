package com.amalia.demoJPA.users.entity;

import com.amalia.demoJPA.currencies.entity.Currencies;
import com.amalia.demoJPA.languages.entity.Languages;
import com.amalia.demoJPA.wallet.entity.Wallet;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Setter
@Getter
@Entity
@Table(name = "users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_id_gen")
    @SequenceGenerator(name = "users_id_gen", sequenceName = "users_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @Column(name = "username", nullable = false, unique = true, length = 50)
    private String username;

    @NotNull
    @Column(name = "email", nullable = false, unique = true, length = 100)
    private String email;

    @NotNull
    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "pin")
    private Integer pin;

    @Column(name = "display_name", length = 50)
    private String displayName;

    @Column(name = "images")
    private String images;

    @Column(name = "quotes")
    private String quotes;

    @NotNull
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "currency_id", referencedColumnName = "id")
    private Currencies currencies;

    @NotNull
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "language_id", referencedColumnName = "id")
    private Languages languages;

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

    @OneToMany(mappedBy = "users")
    private Set<Wallet> wallet = new LinkedHashSet<>();
}
