package com.lucianaugusto.ebanxassignment.event.http.request;

import com.lucianaugusto.ebanxassignment.event.http.enums.EventTypeEnum;

public record EventHttpRequest(EventTypeEnum type, String origin, String destination, Integer amount) {
    public boolean validate() {
        if (type == null) {
            return false;
        }

        if (amount == null) {
            return false;
        }

        return origin != null || destination != null;
    }
}
