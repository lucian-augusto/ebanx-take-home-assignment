package com.lucianaugusto.ebanxassignment.operation.base.orchestration;

import com.lucianaugusto.ebanxassignment.operation.base.request.OperationRequest;
import com.lucianaugusto.ebanxassignment.operation.base.result.OperationResult;

public interface OperationHandler {
    OperationResult handle(OperationRequest operationRequest);
}
