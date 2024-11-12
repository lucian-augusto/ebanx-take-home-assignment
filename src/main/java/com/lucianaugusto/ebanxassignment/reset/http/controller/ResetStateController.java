package com.lucianaugusto.ebanxassignment.reset.http.controller;

import com.lucianaugusto.ebanxassignment.base.http.controller.BaseController;
import com.lucianaugusto.ebanxassignment.reset.orchestration.ResetStateExecutor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("reset")
public class ResetStateController extends BaseController {

    private final ResetStateExecutor executor;

    public ResetStateController(ResetStateExecutor executor) {
        this.executor = executor;
    }

    @PostMapping
    public ResponseEntity<?> resetState() {
        boolean success = executor.resetState();

        if (success) {
            return ResponseEntity.ok("OK");
        }

        return ResponseEntity.internalServerError().build();
    }
}
