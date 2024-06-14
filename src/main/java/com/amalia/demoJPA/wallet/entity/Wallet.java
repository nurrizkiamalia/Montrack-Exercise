package com.amalia.demoJPA.wallet.entity;

import com.amalia.demoJPA.currencies.entity.Currencies;
import com.amalia.demoJPA.users.entity.Users;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import java.time.Instant;

@Setter
@Getter
@Entity
@Table(name = "wallet")
public class Wallet {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "wallet_id_gen")
    @SequenceGenerator(name = "wallet_id_gen", sequenceName = "wallet_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotBlank(message = "Wallet name must not be empty")
    @Column(name = "wallet_name", nullable = false)
    private String walletName;

    @Min(value = 0, message = "Amount of money must be zero or positive")
    @Column(name = "amount", nullable = false)
    private int amount;

    @Column(name = "is_main", nullable = false)
    private boolean isMain;

    @NotNull
    @Column(name = "create_at", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Instant createAt;

    @NotNull
    @Column(name = "update_at", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Instant updateAt;

    @Column(name = "delete_at")
    private Instant deleteAt;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "currency_id", nullable = false)
    private Currencies currencies;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private Users users;

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
