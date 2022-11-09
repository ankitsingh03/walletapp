package com.wba.walletBankingApp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Recharge {
    private String email;
    private String sentTo;
    private int amount;
}
