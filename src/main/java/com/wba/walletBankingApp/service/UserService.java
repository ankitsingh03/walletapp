package com.wba.walletBankingApp.service;

import com.wba.walletBankingApp.entity.Recharge;
import com.wba.walletBankingApp.entity.TransactionEntity;
import com.wba.walletBankingApp.entity.UserEntity;
import com.wba.walletBankingApp.repository.TransactionRepository;
import com.wba.walletBankingApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    TransactionRepository transactionRepository;

    public String signUp(UserEntity user) {
        Optional<UserEntity> existUser = userRepository.findByEmail(user.getEmail());
        if(!existUser.isEmpty()) return "Email already Exist !";
        userRepository.save(user);
        return "signup successfully";
    }

    public String login(UserEntity user) {
        Optional<UserEntity> existUser = userRepository.findByEmail(user.getEmail());
        if(existUser.isEmpty()) return "Please check your email !";
        if(user.getPassword().equals(existUser.get().getPassword())) return "User logged in";
        return "Please check your password";
    }

    public String walletRecharge(Recharge recharge) {
        String email = recharge.getEmail();
        int amount = recharge.getAmount();

        if(amount <= 0) return "Invalid amount: " + amount;

        UserEntity existUser = userRepository.findByEmail(email).get();
        int prevAmount = existUser.getAmount();
        existUser.setAmount(prevAmount + amount);
        userRepository.save(existUser);
        createTransaction(existUser.getEmail(), "Bank", existUser.getEmail(), "Credit", amount);
        return  Integer.toString(existUser.getAmount());
    }

    public String amountTransfer(Recharge recharge) {
        Optional<UserEntity> senderUser = userRepository.findByEmail(recharge.getSentTo());
        Optional<UserEntity> user = userRepository.findByEmail(recharge.getEmail());
        if(senderUser.isEmpty()) return "Please check your sender mail !";
        if(user.isEmpty()) return "Please check your mail !";

        int tranferAmount = recharge.getAmount();
        if(tranferAmount <= 0) return "Invalid amount: " + tranferAmount;

        int userAmount = user.get().getAmount();
        if(userAmount < tranferAmount) return "Insufficient amount: " + userAmount;

        senderUser.get().setAmount(senderUser.get().getAmount() + tranferAmount);
        user.get().setAmount(user.get().getAmount() - tranferAmount);
        userRepository.save(senderUser.get());
        userRepository.save(user.get());
        return Integer.toString(senderUser.get().getAmount());
    }

    private void createTransaction(String email, String from, String sentTo, String type, int amount){
        TransactionEntity tran = new TransactionEntity();
        tran.setEmail(email);
        tran.setFrom(from);
        tran.setSentTo(sentTo);
        tran.setAmount(amount);
        tran.setTransType(type);
        transactionRepository.save(tran);
    }
}
