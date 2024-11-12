package com.lucianaugusto.ebanxassignment.event.operation.base.result;

public record OperationResult(
    boolean isSuccess,
    BalanceInfo origin,
    BalanceInfo destination
){
    public boolean hasOrigin() {
        return origin != null;
    }

    public boolean hasDestination() {
        return destination != null;
    }
}
