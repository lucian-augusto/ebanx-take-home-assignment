package com.lucianaugusto.ebanxassignment.event.operation;

public interface OperationExecutor {
    OperationTypeEnum getOperationType();
    OperationResult execute(OperationRequest operationRequest);
}
