package com.wba.walletBankingApp.service;

import com.wba.walletBankingApp.entity.TransactionEntity;
import com.wba.walletBankingApp.entity.UserEntity;
import com.wba.walletBankingApp.repository.TransactionRepository;
import com.wba.walletBankingApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;
    public List<TransactionEntity> fetchTransaction(String email) {
        return transactionRepository.findAllByEmail(email);
    }
}
