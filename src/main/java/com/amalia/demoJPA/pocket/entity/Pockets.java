package com.amalia.demoJPA.pocket.entity;

import com.amalia.demoJPA.users.entity.Users;
import com.amalia.demoJPA.wallet.entity.Wallet;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Setter
@Getter
@Entity
@Table(name = "pockets")
public class Pockets {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pockets_id_gen")
    @SequenceGenerator(name = "pockets_id_gen", sequenceName = "pockets_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private int id;

    @NotNull
    @Column(name = "pocket_name", nullable = false)
    private String pocketName;

    @NotNull
    @Column(name = "amount", nullable = false)
    private int amount;

    @NotNull
    @Column(name = "description")
    private String description;

    @Column(name = "emoji")
    private String emoji;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "wallet_id", nullable = false)
    private Wallet wallet;

    @NotNull
    @Column(name = "create_at", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Instant createAt;

    @NotNull
    @Column(name = "update_at", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Instant updateAt;

    @Column(name = "delete_at")
    private Instant deleteAt;

    @PrePersist
    protected void onCreate() {
        createAt = Instant.now();
        updateAt = Instant.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updateAt = Instant.now();
    }
}
