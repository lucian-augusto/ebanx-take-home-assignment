package com.lucianaugusto.ebanxassignment.event.http.controller;

import com.lucianaugusto.ebanxassignment.event.http.enums.EventTypeEnum;
import com.lucianaugusto.ebanxassignment.event.http.request.EventHttpRequest;
import com.lucianaugusto.ebanxassignment.event.operation.OperationTypeEnum;
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

    private final AccountService accountService;

    private final Map<EventTypeEnum, OperationTypeEnum> eventOperationMap = new HashMap<>();

    public EventController(AccountService accountService) {
        this.accountService = accountService;
        populateEventOperationMap();
    }

    @PostMapping
    public ResponseEntity<?> processEvent(@RequestBody EventHttpRequest request) {
        if (!request.validate()) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(eventOperationMap);
    }

    private void populateEventOperationMap() {
        Arrays.stream(EventTypeEnum.values()).forEach(v -> eventOperationMap.put(v, convertEventTypeToOperationType(v)));
    }

    private OperationTypeEnum convertEventTypeToOperationType(EventTypeEnum eventType) {
        return OperationTypeEnum.valueOf(eventType.name().toUpperCase());
    }
}

