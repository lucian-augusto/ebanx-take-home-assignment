package com.lucianaugusto.ebanxassignment.event.http.controller;

import com.lucianaugusto.ebanxassignment.event.http.enums.EventTypeEnum;
import com.lucianaugusto.ebanxassignment.event.http.request.EventHttpRequest;
import com.lucianaugusto.ebanxassignment.event.http.response.EventHttpResponse;
import com.lucianaugusto.ebanxassignment.event.http.response.EventAccountInfo;
import com.lucianaugusto.ebanxassignment.event.operation.OperationHandler;
import com.lucianaugusto.ebanxassignment.event.operation.OperationRequest;
import com.lucianaugusto.ebanxassignment.event.operation.OperationResult;
import com.lucianaugusto.ebanxassignment.event.operation.OperationTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("event")
public class EventController {

    private final OperationHandler operationHandler;

    private final Map<EventTypeEnum, OperationTypeEnum> eventOperationMap = new HashMap<>();

    @Autowired
    public EventController(OperationHandler operationHandler) {
        this.operationHandler = operationHandler;
        populateEventOperationMap();
    }

    @PostMapping
    public ResponseEntity<?> processEvent(@RequestBody EventHttpRequest request) {
        if (!request.validate()) {
            return ResponseEntity.badRequest().build();
        }

        OperationRequest operationRequest = new OperationRequest(
                eventOperationMap.get(request.type()),
                request.amount()
        );
        operationRequest.setOriginAccountNumber(request.origin());
        operationRequest.setDestinationAccountNumber(request.destination());

        OperationResult result = operationHandler.handle(operationRequest);

        if (result.isSuccess()) {
            return ResponseEntity.ok(new EventHttpResponse(
                    result.hasOrigin()
                            ? new EventAccountInfo(result.origin().accountNumber(), result.origin().amount())
                            : null,
                    result.hasDestination()
                            ? new EventAccountInfo(result.destination().accountNumber(), result.destination().amount())
                            : null
            ));
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(0);
    }

    private void populateEventOperationMap() {
        Arrays.stream(EventTypeEnum.values()).forEach(v -> eventOperationMap.put(v, convertEventTypeToOperationType(v)));
    }

    private OperationTypeEnum convertEventTypeToOperationType(EventTypeEnum eventType) {
        return OperationTypeEnum.valueOf(eventType.name().toUpperCase());
    }
}

