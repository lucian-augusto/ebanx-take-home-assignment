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
        ).orElseThrow(AccountNotFoundException::new);
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
