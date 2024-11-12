package com.lucianaugusto.ebanxassignment.event.operation.deposit;

import com.lucianaugusto.ebanxassignment.account.model.Account;
import com.lucianaugusto.ebanxassignment.account.service.AccountService;
import com.lucianaugusto.ebanxassignment.balance.model.Balance;
import com.lucianaugusto.ebanxassignment.balance.service.BalanceService;
import com.lucianaugusto.ebanxassignment.event.operation.base.enums.OperationTypeEnum;
import com.lucianaugusto.ebanxassignment.event.operation.base.orchestrator.OperationExecutor;
import com.lucianaugusto.ebanxassignment.event.operation.base.request.OperationRequest;
import com.lucianaugusto.ebanxassignment.event.operation.base.result.BalanceInfo;
import com.lucianaugusto.ebanxassignment.event.operation.base.result.OperationResult;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DepositExecutor implements OperationExecutor {

    private static final OperationTypeEnum type = OperationTypeEnum.DEPOSIT;

    private final AccountService accountService;

    private final BalanceService balanceService;

    public DepositExecutor(AccountService accountService, BalanceService balanceService) {
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
                .findByAccountNumber(operationRequest.getDestinationAccountNumber());

        return accountOptional.map(account -> updateAccountBalance(account, operationRequest.getAmount()))
                .orElseGet(() -> createAccountWithInitialBalance(
                    operationRequest.getDestinationAccountNumber(),
                    operationRequest.getAmount()
                ));
    }

    private OperationResult createAccountWithInitialBalance(String accountNumber, Integer amount) {
        Account account = accountService.createAccount(accountNumber);
        Balance balance = balanceService.createBalance(account, amount);

        return new OperationResult(true, null, new BalanceInfo(balance));
    }

    private OperationResult updateAccountBalance(Account account, Integer amount) {
        Balance balance = balanceService.deposit(account.getBalance(), amount);
        return new OperationResult(true, null, new BalanceInfo(balance));
    }
}
