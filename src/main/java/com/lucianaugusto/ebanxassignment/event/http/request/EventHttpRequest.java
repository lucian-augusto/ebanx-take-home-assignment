package com.lucianaugusto.ebanxassignment.event.http.request;

import com.lucianaugusto.ebanxassignment.event.http.enums.EventTypeEnum;

import java.math.BigDecimal;

public record EventHttpRequest(EventTypeEnum type, String destination, BigDecimal amount) {}
