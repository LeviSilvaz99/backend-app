package com.levisilva.exception;

public class SenhaInvalidaException extends RuntimeException {
    public SenhaInvalidaException() {
        super("SENHA INVALIDA");
    }
}
