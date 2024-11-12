package com.lucianaugusto.ebanxassignment.event.operation.base.orchestrator;

import com.lucianaugusto.ebanxassignment.event.operation.base.request.OperationRequest;
import com.lucianaugusto.ebanxassignment.event.operation.base.result.OperationResult;

public interface OperationHandler {
    OperationResult handle(OperationRequest operationRequest);
}
