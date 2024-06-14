package com.amalia.demoJPA.currencies.entity;

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
@Table(name = "currencies")
public class Currencies {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "currencies_id_gen")
    @SequenceGenerator(name = "currencies_id_gen", sequenceName = "currencies_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private int id;

    @NotNull
    @Column(name = "currency_name", nullable = false)
    private String currencyName;

    @NotNull
    @OneToMany(mappedBy = "currencies")
    private Set<Wallet> wallets = new LinkedHashSet<>();

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
