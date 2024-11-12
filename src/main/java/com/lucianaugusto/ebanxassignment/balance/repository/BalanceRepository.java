package com.lucianaugusto.ebanxassignment.balance.repository;

import com.lucianaugusto.ebanxassignment.balance.model.Balance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BalanceRepository extends JpaRepository<Balance, Long> {
    // NTD
}
