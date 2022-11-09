package com.wba.walletBankingApp.repository;

import com.wba.walletBankingApp.entity.TransactionEntity;
import com.wba.walletBankingApp.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {
    public List<TransactionEntity> findAllByEmail(String email);
}

