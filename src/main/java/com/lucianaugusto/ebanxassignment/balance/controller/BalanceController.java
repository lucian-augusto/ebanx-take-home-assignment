package com.lucianaugusto.ebanxassignment.balance.controller;

import com.lucianaugusto.ebanxassignment.account.model.Account;
import com.lucianaugusto.ebanxassignment.account.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("balance")
public class BalanceController {

    private final AccountService accountService;

    public BalanceController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping
    public ResponseEntity<?> getBalance(@RequestParam(name = "account_id") String accountNumber) {
        Optional<Account> accountOptional = accountService.findByAccountNumber(accountNumber);
        if (accountOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(0);
        }

        return ResponseEntity.ok(accountOptional.get().getBalance().getAmount());
    }
}

