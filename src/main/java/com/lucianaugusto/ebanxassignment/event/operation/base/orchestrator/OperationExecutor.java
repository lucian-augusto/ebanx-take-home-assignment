package com.lucianaugusto.ebanxassignment.event.operation.base.orchestrator;

import com.lucianaugusto.ebanxassignment.event.operation.base.request.OperationRequest;
import com.lucianaugusto.ebanxassignment.event.operation.base.result.OperationResult;
import com.lucianaugusto.ebanxassignment.event.operation.base.enums.OperationTypeEnum;

public interface OperationExecutor {
    OperationTypeEnum getOperationType();
    OperationResult execute(OperationRequest operationRequest);
}
