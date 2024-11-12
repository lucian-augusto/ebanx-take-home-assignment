package com.lucianaugusto.ebanxassignment.event.operation.transfer;

import com.lucianaugusto.ebanxassignment.account.model.Account;
import com.lucianaugusto.ebanxassignment.account.service.AccountService;
import com.lucianaugusto.ebanxassignment.balance.model.Balance;
import com.lucianaugusto.ebanxassignment.balance.service.BalanceService;
import com.lucianaugusto.ebanxassignment.event.operation.*;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Optional;

@Component
public class TransferExecutor implements OperationExecutor {
    private static final OperationTypeEnum type = OperationTypeEnum.TRANSFER;

    private final AccountService accountService;
    private final BalanceService balanceService;

    public TransferExecutor(AccountService accountService, BalanceService balanceService) {
        this.accountService = accountService;
        this.balanceService = balanceService;
    }

    @Override
    public OperationTypeEnum getOperationType() {
        return type;
    }

    @Override
    public OperationResult execute(OperationRequest operationRequest) {
        Optional<Account> originAccountOptional = accountService
                .findByAccountNumber(operationRequest.getOriginAccountNumber());
        return originAccountOptional.map(
                origin -> executeTransfer(
                        origin,
                        operationRequest.getAmount(),
                        operationRequest.getDestinationAccountNumber()
                )
        ).orElseGet(() -> {
                    return new OperationResult(false, null, null);
                }
        );
    }

    private OperationResult executeTransfer(Account origin, Integer amount, String destinationAccountNumber) {
        Balance balance = balanceService.sendTransfer(origin.getBalance(), amount);
        return new OperationResult(
                true,
                new BalanceInfo(balance.getAccountNumber(), balance.getAmount()),
                new BalanceInfo(destinationAccountNumber, amount)
        );
    }
}