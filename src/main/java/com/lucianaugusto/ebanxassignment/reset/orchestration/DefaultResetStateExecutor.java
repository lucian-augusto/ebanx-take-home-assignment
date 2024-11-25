package com.lucianaugusto.ebanxassignment.reset.orchestration;

import com.lucianaugusto.ebanxassignment.account.model.Account;
import com.lucianaugusto.ebanxassignment.account.repository.AccountRepository;
import com.lucianaugusto.ebanxassignment.balance.model.Balance;
import com.lucianaugusto.ebanxassignment.balance.repository.BalanceRepository;
import org.springframework.stereotype.Component;

@Component
public class DefaultResetStateExecutor implements ResetStateExecutor {

    private final AccountRepository accountRepository;
    private final BalanceRepository balanceRepository;

    private static final String DEFAULT_EXISTING_ACCOUNT_NUMBER = "300";

    public DefaultResetStateExecutor(AccountRepository accountRepository, BalanceRepository balanceRepository) {
        this.accountRepository = accountRepository;
        this.balanceRepository = balanceRepository;
    }

    @Override
    public boolean resetState() {
        try {
            clearDatabase();
            repopulateInitialDatabaseState();
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    private void clearDatabase() {
        balanceRepository.deleteAll();
        accountRepository.deleteAll();
    }

    private void repopulateInitialDatabaseState() {
        Account account = new Account(DEFAULT_EXISTING_ACCOUNT_NUMBER);
        Balance balance = new Balance(account, 0);
        accountRepository.save(account);
        balanceRepository.save(balance);
    }
}
