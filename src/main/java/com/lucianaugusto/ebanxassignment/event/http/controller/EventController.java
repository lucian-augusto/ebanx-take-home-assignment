package com.lucianaugusto.ebanxassignment.event.http.controller;

import com.lucianaugusto.ebanxassignment.account.service.AccountService;
import com.lucianaugusto.ebanxassignment.event.http.request.EventHttpRequest;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("event")
public class EventController {

    private final AccountService accountService;

    public EventController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public ResponseEntity<?> processEvent(@RequestBody EventHttpRequest request) {
        return ResponseEntity.ok(request);
    }
}

