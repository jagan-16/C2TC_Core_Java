package com.mall.shopowner.exception;

/**
 * Custom exception thrown when a ShopOwner is not found.
 */
public class ShopOwnerNotFoundException extends RuntimeException {

    public ShopOwnerNotFoundException(String message) {
        super(message);
    }

    public ShopOwnerNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
