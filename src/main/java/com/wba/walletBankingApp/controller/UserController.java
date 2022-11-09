package com.wba.walletBankingApp.controller;

import com.wba.walletBankingApp.entity.Recharge;
import com.wba.walletBankingApp.entity.UserEntity;
import com.wba.walletBankingApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public String signUp(@RequestBody UserEntity user){
        return userService.signUp(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody UserEntity user){
        return userService.login(user);
    }

    @PutMapping("/recharge")
    public String walletRecharge(@RequestBody Recharge recharge){
        return userService.walletRecharge(recharge);
    }

    @PutMapping("/transfer")
    public String amountTransfer(@RequestBody Recharge recharge){
        return userService.amountTransfer(recharge);
    }
}
