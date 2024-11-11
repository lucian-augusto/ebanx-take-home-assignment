package com.lucianaugusto.ebanxassignment.event.operation.withdraw;

import com.lucianaugusto.ebanxassignment.account.model.Account;
import com.lucianaugusto.ebanxassignment.account.service.AccountService;
import com.lucianaugusto.ebanxassignment.balance.model.Balance;
import com.lucianaugusto.ebanxassignment.balance.service.BalanceService;
import com.lucianaugusto.ebanxassignment.event.operation.*;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class WithdrawExecutor implements OperationExecutor {

    private static final OperationTypeEnum type = OperationTypeEnum.WITHDRAW;

    private final AccountService accountService;

    private final BalanceService balanceService;

    public WithdrawExecutor(AccountService accountService, BalanceService balanceService) {
        this.accountService = accountService;
        this.balanceService = balanceService;
    }

    @Override
    public OperationTypeEnum getOperationType() {
        return type;
    }

    @Override
    public OperationResult execute(OperationRequest operationRequest) {
        Optional<Account> accountOptional = accountService
                .findByAccountNumber(operationRequest.getOriginAccountNumber());

        return accountOptional.map(account -> {
            Balance balance = balanceService.withdraw(account.getBalance(), operationRequest.getAmount());
            return new OperationResult(true, new BalanceInfo(balance), null);
        }).orElseGet(() -> {
            return new OperationResult(false, null, null);
        });
    }
}
