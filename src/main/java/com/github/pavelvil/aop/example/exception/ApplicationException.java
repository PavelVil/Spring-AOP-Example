package com.github.pavelvil.aop.example.exception;

import com.github.pavelvil.aop.example.annotation.Throw;

@Throw
public class ApplicationException extends RuntimeException {

    public ApplicationException() {
    }

    public ApplicationException(String message) {
        super(message);
    }

    public ApplicationException(Throwable cause) {
        super(cause);
    }
}
