package com.lucianaugusto.ebanxassignment.reset.orchestration;

import com.lucianaugusto.ebanxassignment.account.repository.AccountRepository;
import com.lucianaugusto.ebanxassignment.balance.repository.BalanceRepository;
import org.springframework.stereotype.Component;

@Component
public class DefaultResetStateExecutor implements ResetStateExecutor {

    private final AccountRepository accountRepository;
    private final BalanceRepository balanceRepository;

    public DefaultResetStateExecutor(AccountRepository accountRepository, BalanceRepository balanceRepository) {
        this.accountRepository = accountRepository;
        this.balanceRepository = balanceRepository;
    }

    @Override
    public boolean resetState() {
        try {
            balanceRepository.deleteAll();
            accountRepository.deleteAll();
        } catch (Exception e) {
            return false;
        }

        return true;
    }
}
