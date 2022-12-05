package com.xrest.nchl.exception;

import com.xrest.nchl.enums.ExceptionTypes;

public class BankException extends BaseException {
    public BankException(ExceptionTypes exceptionTypes) {
        super("Bank " + exceptionTypes.label);
    }
}

