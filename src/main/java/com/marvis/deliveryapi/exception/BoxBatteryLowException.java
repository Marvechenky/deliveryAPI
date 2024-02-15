package com.marvis.deliveryapi.exception;

public class BoxBatteryLowException extends RuntimeException {
    public BoxBatteryLowException(String message) {
        super(message);
    }
}
