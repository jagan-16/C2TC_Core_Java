package com.mall.admin.exception;

/**
 * Custom exception thrown when an Admin is not found.
 */
public class AdminNotFoundException extends RuntimeException {

    public AdminNotFoundException(String message) {
        super(message);
    }

    public AdminNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
