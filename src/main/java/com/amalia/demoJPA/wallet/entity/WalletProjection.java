package com.amalia.demoJPA.wallet.entity;
public interface WalletProjection {
    Long getId();
    String getWalletName();
    int getAmount();
    int getCurrenciesId();
    int getUsersId();
}