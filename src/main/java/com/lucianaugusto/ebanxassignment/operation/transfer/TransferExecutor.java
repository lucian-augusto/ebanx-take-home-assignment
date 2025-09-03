package com.lucianaugusto.ebanxassignment.operation.transfer;

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
public class TransferExecutor implements OperationExecutor {

    private final AccountService accountService;
    private final BalanceService balanceService;

    public TransferExecutor(AccountService accountService, BalanceService balanceService) {
        this.accountService = accountService;
        this.balanceService = balanceService;
    }

    @Override
    public OperationTypeEnum getOperationType() {
        return OperationTypeEnum.TRANSFER;
    }

    @Override
    public OperationResult execute(OperationRequest operationRequest) {
        Optional<Account> originAccountOptional = accountService
                .findByAccountNumber(operationRequest.getOriginAccountNumber());
        Optional<Account> destinationAccountOptional = accountService
                .findByAccountNumber(operationRequest.getDestinationAccountNumber());

        if (originAccountOptional.isEmpty() || destinationAccountOptional.isEmpty()) {
            throw new AccountNotFoundException();
        }

        return executeTransfer(originAccountOptional.get(), destinationAccountOptional.get(), operationRequest.getAmount());
    }

    private OperationResult executeTransfer(Account origin, Account destination, Integer amount) {
        Balance originBalance = balanceService.sendTransfer(origin.getBalance(), amount);
        Balance destinationBalance = balanceService.receiveTransfer(destination.getBalance(), amount);
        return new OperationResult(
                true,
                new BalanceInfo(originBalance.getAccountNumber(), originBalance.getAmount()),
                new BalanceInfo(destinationBalance.getAccountNumber(), destinationBalance.getAmount())
        );
    }
}
