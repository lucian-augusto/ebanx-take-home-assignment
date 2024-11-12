package com.lucianaugusto.ebanxassignment.account.error;

import com.lucianaugusto.ebanxassignment.base.error.ValidationException;

public class AccountNotFoundException extends ValidationException {
    public AccountNotFoundException(String message) {
        super(message);
    }

    public AccountNotFoundException() {
      this("User not found");
    }
}
