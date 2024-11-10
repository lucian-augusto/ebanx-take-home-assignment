package com.lucianaugusto.ebanxassignment.event.http.request;

import com.lucianaugusto.ebanxassignment.event.http.enums.EventTypeEnum;

import java.math.BigDecimal;

public record EventHttpRequest(EventTypeEnum type, String origin, String destination, BigDecimal amount) {
    public boolean validate() {
        if (type == null) {
            return false;
        }

        if (amount == null) {
            return false;
        }

        if (origin == null && destination == null) {
            return false;
        }

        return true;
    }
}
