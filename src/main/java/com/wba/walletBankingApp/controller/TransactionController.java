package com.wba.walletBankingApp.controller;

import com.wba.walletBankingApp.entity.TransactionEntity;
import com.wba.walletBankingApp.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping("/transaction/{email}")
    public List<TransactionEntity> fetchTransaction(@PathVariable String email){
        return transactionService.fetchTransaction(email);
    }
}
