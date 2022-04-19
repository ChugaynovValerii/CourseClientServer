package ru.spbstu.exception;

import org.springframework.security.core.AuthenticationException;

public class InvalidPasswordException extends AuthenticationException {
    public InvalidPasswordException(String message) {
        super(message);
    }
}