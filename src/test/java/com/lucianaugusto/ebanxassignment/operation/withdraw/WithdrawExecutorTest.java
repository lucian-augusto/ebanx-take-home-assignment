package com.lucianaugusto.ebanxassignment.operation.withdraw;

import com.lucianaugusto.ebanxassignment.account.error.AccountNotFoundException;
import com.lucianaugusto.ebanxassignment.account.model.Account;
import com.lucianaugusto.ebanxassignment.account.service.AccountService;
import com.lucianaugusto.ebanxassignment.balance.model.Balance;
import com.lucianaugusto.ebanxassignment.balance.service.BalanceService;
import com.lucianaugusto.ebanxassignment.base.BaseTest;
import com.lucianaugusto.ebanxassignment.operation.base.enums.OperationTypeEnum;
import com.lucianaugusto.ebanxassignment.operation.base.request.OperationRequest;
import com.lucianaugusto.ebanxassignment.operation.base.result.OperationResult;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.lang.reflect.Field;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class WithdrawExecutorTest extends BaseTest {

    @InjectMocks
    private WithdrawExecutor executor;

    @Mock
    private AccountService accountService;

    @Mock
    private BalanceService balanceService;

    @Test
    void getOperationType() {
        Assertions.assertEquals(OperationTypeEnum.WITHDRAW, executor.getOperationType());
    }

    @Test
    void executeNonExistingAccount() {
        String accountNumber = "7777";
        OperationRequest request = new OperationRequest(OperationTypeEnum.TRANSFER, 10);
        request.setOriginAccountNumber(accountNumber);

        Mockito.when(accountService.findByAccountNumber(accountNumber)).thenReturn(Optional.empty());
        Assertions.assertThrows(AccountNotFoundException.class, () -> executor.execute(request));
    }

    @Test
    void executeExistingAccount() throws NoSuchFieldException, IllegalAccessException {
        String accountNumber = "7777";
        String destinationNumber = "999";
        Integer amount = 10;
        Account account = new Account(accountNumber);
        Balance balance = new Balance(account, amount);
        Balance expectedBalance = new Balance(account, 0);
        Field accountBalanceField = account.getClass().getDeclaredField("balance");
        accountBalanceField.setAccessible(true);
        accountBalanceField.set(account, balance);
        accountBalanceField.setAccessible(false);

        OperationRequest request = new OperationRequest(OperationTypeEnum.TRANSFER, 10);
        request.setOriginAccountNumber(accountNumber);
        request.setDestinationAccountNumber(destinationNumber);

        Mockito.when(accountService.findByAccountNumber(accountNumber)).thenReturn(Optional.of(account));
        Mockito.when(balanceService.withdraw(balance, amount)).thenReturn(expectedBalance);
        OperationResult result = executor.execute(request);

        Assertions.assertTrue(result.isSuccess());
        Assertions.assertEquals(accountNumber, result.origin().accountNumber());
        Assertions.assertEquals(0, result.origin().amount());
        Assertions.assertNull(result.destination());
    }
}