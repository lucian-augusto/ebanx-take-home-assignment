package com.lucianaugusto.ebanxassignment.operation.base.orchestration;

import com.lucianaugusto.ebanxassignment.operation.base.request.OperationRequest;
import com.lucianaugusto.ebanxassignment.operation.base.result.OperationResult;
import com.lucianaugusto.ebanxassignment.operation.base.enums.OperationTypeEnum;

public interface OperationExecutor {
    OperationTypeEnum getOperationType();
    OperationResult execute(OperationRequest operationRequest);
}
