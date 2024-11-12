package com.lucianaugusto.ebanxassignment.operation.base.orchestration;

import com.lucianaugusto.ebanxassignment.operation.base.request.OperationRequest;
import com.lucianaugusto.ebanxassignment.operation.base.result.OperationResult;
import com.lucianaugusto.ebanxassignment.operation.base.enums.OperationTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class DefaultOperationHandler implements OperationHandler {

    private final Map<OperationTypeEnum, OperationExecutor> operationHandlerMap;

    @Autowired
    private DefaultOperationHandler(List<OperationExecutor> executors) {
        operationHandlerMap = executors
                .stream()
                .collect(Collectors.toUnmodifiableMap(
                        OperationExecutor::getOperationType,
                        Function.identity())
                );
    }

    @Override
    public OperationResult handle(OperationRequest operationRequest) {
        return operationHandlerMap.get(operationRequest.getType()).execute(operationRequest);
    }
}
