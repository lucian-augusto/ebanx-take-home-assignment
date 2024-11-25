package com.lucianaugusto.ebanxassignment.operation.transfer;

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

class TransferExecutorTest extends BaseTest {

    @InjectMocks
    private TransferExecutor executor;

    @Mock
    private AccountService accountService;

    @Mock
    private BalanceService balanceService;

    @Test
    void getOperationType() {
        Assertions.assertEquals(OperationTypeEnum.TRANSFER, executor.getOperationType());
    }

    @Test
    void executeNonExistingOriginAccount() {
        String originAccountNumber = "7777";
        String destinationAccountNumber = "999";
        Account destinationAccount = new Account(destinationAccountNumber);
        OperationRequest request = new OperationRequest(OperationTypeEnum.TRANSFER, 10);
        request.setOriginAccountNumber(originAccountNumber);
        request.setDestinationAccountNumber(destinationAccountNumber);

        Mockito.when(accountService.findByAccountNumber(originAccountNumber)).thenReturn(Optional.empty());
        Mockito.when(accountService.findByAccountNumber(destinationAccountNumber)).thenReturn(Optional.of(destinationAccount));
        Assertions.assertThrows(AccountNotFoundException.class, () -> executor.execute(request));
    }

    @Test
    void executeNonExistingDestinationAccount() {
        String originAccountNumber = "7777";
        String destinationAccountNumber = "999";
        Account originAccount = new Account(originAccountNumber);
        OperationRequest request = new OperationRequest(OperationTypeEnum.TRANSFER, 10);
        request.setOriginAccountNumber(originAccountNumber);
        request.setDestinationAccountNumber(destinationAccountNumber);

        Mockito.when(accountService.findByAccountNumber(originAccountNumber)).thenReturn(Optional.of(originAccount));
        Mockito.when(accountService.findByAccountNumber(destinationAccountNumber)).thenReturn(Optional.empty());
        Assertions.assertThrows(AccountNotFoundException.class, () -> executor.execute(request));
    }

    @Test
    void executeExistingAccounts() throws NoSuchFieldException, IllegalAccessException {
        String accountNumber = "7777";
        String destinationNumber = "999";
        Integer amount = 10;
        Account originAccount = new Account(accountNumber);
        Balance originBalance = new Balance(originAccount, amount);
        Balance expectedOriginBalance = new Balance(originAccount, 0);
        Account destinationAccount = new Account(destinationNumber);
        Balance destinationBalance = new Balance(destinationAccount, 0);
        Balance expectedDestinationBalance = new Balance(destinationAccount, amount);
        Field accountBalanceField = originAccount.getClass().getDeclaredField("balance");
        accountBalanceField.setAccessible(true);
        accountBalanceField.set(originAccount, originBalance);
        accountBalanceField.set(destinationAccount, destinationBalance);
        accountBalanceField.setAccessible(false);

        OperationRequest request = new OperationRequest(OperationTypeEnum.TRANSFER, 10);
        request.setOriginAccountNumber(accountNumber);
        request.setDestinationAccountNumber(destinationNumber);

        Mockito.when(accountService.findByAccountNumber(accountNumber)).thenReturn(Optional.of(originAccount));
        Mockito.when(accountService.findByAccountNumber(destinationNumber)).thenReturn(Optional.of(destinationAccount));
        Mockito.when(balanceService.sendTransfer(originBalance, amount)).thenReturn(expectedOriginBalance);
        Mockito.when(balanceService.receiveTransfer(destinationBalance, amount)).thenReturn(expectedDestinationBalance);
        OperationResult result = executor.execute(request);

        Assertions.assertTrue(result.isSuccess());
        Assertions.assertEquals(accountNumber, result.origin().accountNumber());
        Assertions.assertEquals(0, result.origin().amount());
        Assertions.assertEquals(destinationNumber, result.destination().accountNumber());
        Assertions.assertEquals(amount, result.destination().amount());
    }
}