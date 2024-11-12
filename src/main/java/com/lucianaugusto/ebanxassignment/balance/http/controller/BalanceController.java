package com.lucianaugusto.ebanxassignment.balance.http.controller;

import com.lucianaugusto.ebanxassignment.account.error.AccountNotFoundException;
import com.lucianaugusto.ebanxassignment.account.model.Account;
import com.lucianaugusto.ebanxassignment.account.service.AccountService;
import com.lucianaugusto.ebanxassignment.base.http.controller.BaseController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("balance")
public class BalanceController extends BaseController {

    private final AccountService accountService;

    public BalanceController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping
    public ResponseEntity<?> getBalance(@RequestParam(name = "account_id") String accountNumber) {
        Optional<Account> accountOptional = accountService.findByAccountNumber(accountNumber);
        if (accountOptional.isEmpty()) {
            throw new AccountNotFoundException();
        }

        return ResponseEntity.ok(accountOptional.get().getBalance().getAmount());
    }
}

