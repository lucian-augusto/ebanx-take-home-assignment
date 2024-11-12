package com.lucianaugusto.ebanxassignment.reset.http.controller;

import com.lucianaugusto.ebanxassignment.base.BaseTest;
import com.lucianaugusto.ebanxassignment.reset.orchestration.ResetStateExecutor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

class ResetStateControllerTest extends BaseTest {

    @InjectMocks
    private ResetStateController controller;

    @Mock
    private ResetStateExecutor executor;

    @Test
    void resetStateSuccessful() {
        Mockito.when(executor.resetState()).thenReturn(true);
        ResponseEntity<?> response = controller.resetState();

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals("OK", response.getBody());
    }

    @Test
    void resetStateError() {
        Mockito.when(executor.resetState()).thenReturn(false);
        ResponseEntity<?> response = controller.resetState();

        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }
}