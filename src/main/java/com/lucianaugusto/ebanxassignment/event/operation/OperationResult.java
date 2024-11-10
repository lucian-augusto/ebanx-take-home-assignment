package com.lucianaugusto.ebanxassignment.event.operation;

import com.lucianaugusto.ebanxassignment.account.model.Account;

import java.math.BigDecimal;

public record OperationResult(
    boolean isSuccess,
    String destinationAccount,
    BigDecimal destinationBalance
){}
