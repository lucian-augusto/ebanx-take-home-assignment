package com.lucianaugusto.ebanxassignment.event.operation;

import java.math.BigDecimal;

public class OperationRequest {
    private final OperationTypeEnum type;
    private final Integer amount;
    private String originAccountNumber;
    private String destinationAccountNumber;

    public OperationRequest(OperationTypeEnum type, Integer amount) {
        this.type = type;
        this.amount = amount;
    }

    public OperationTypeEnum getType() {
        return type;
    }

    public Integer getAmount() {
        return amount;
    }

    public String getOriginAccountNumber() {
        return originAccountNumber;
    }

    public void setOriginAccountNumber(String originAccountNumber) {
        this.originAccountNumber = originAccountNumber;
    }

    public String getDestinationAccountNumber() {
        return destinationAccountNumber;
    }

    public void setDestinationAccountNumber(String destinationAccountNumber) {
        this.destinationAccountNumber = destinationAccountNumber;
    }
}
