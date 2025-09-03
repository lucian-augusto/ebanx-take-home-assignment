package com.lucianaugusto.ebanxassignment.operation.withdraw;

import com.lucianaugusto.ebanxassignment.account.error.AccountNotFoundException;
import com.lucianaugusto.ebanxassignment.account.model.Account;
import com.lucianaugusto.ebanxassignment.account.service.AccountService;
import com.lucianaugusto.ebanxassignment.balance.model.Balance;
import com.lucianaugusto.ebanxassignment.balance.service.BalanceService;
import com.lucianaugusto.ebanxassignment.operation.base.enums.OperationTypeEnum;
import com.lucianaugusto.ebanxassignment.operation.base.orchestration.OperationExecutor;
import com.lucianaugusto.ebanxassignment.operation.base.request.OperationRequest;
import com.lucianaugusto.ebanxassignment.operation.base.result.BalanceInfo;
import com.lucianaugusto.ebanxassignment.operation.base.result.OperationResult;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class WithdrawExecutor implements OperationExecutor {

    private final AccountService accountService;

    private final BalanceService balanceService;

    public WithdrawExecutor(AccountService accountService, BalanceService balanceService) {
        this.accountService = accountService;
        this.balanceService = balanceService;
    }

    @Override
    public OperationTypeEnum getOperationType() {
        return OperationTypeEnum.WITHDRAW;
    }

    @Override
    public OperationResult execute(OperationRequest operationRequest) {
        Optional<Account> accountOptional = accountService
                .findByAccountNumber(operationRequest.getOriginAccountNumber());

        return accountOptional.map(account -> {
            Balance balance = balanceService.withdraw(account.getBalance(), operationRequest.getAmount());
            return new OperationResult(true, new BalanceInfo(balance), null);
        }).orElseThrow(AccountNotFoundException::new);
    }
}
