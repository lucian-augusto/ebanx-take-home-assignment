package com.lucianaugusto.ebanxassignment.event.operation;

public record OperationResult(
    boolean isSuccess,
    BalanceInfo origin,
    BalanceInfo destination
){}
