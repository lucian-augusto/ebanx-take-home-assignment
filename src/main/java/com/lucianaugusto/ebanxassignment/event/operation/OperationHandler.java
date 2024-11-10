package com.lucianaugusto.ebanxassignment.event.operation;

public interface OperationHandler {
    OperationResult handle(OperationRequest operationRequest);
}
