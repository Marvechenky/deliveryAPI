package com.marvis.deliveryapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BoxNotFoundException.class)
    public ResponseEntity<String> handleBoxNotFoundException(BoxNotFoundException boxNotFoundException){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(boxNotFoundException.getMessage());
    }

    @ExceptionHandler(BoxStateException.class)
    public ResponseEntity<String> handleBoxStateException(BoxStateException boxStateException) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(boxStateException.getMessage());
    }

    @ExceptionHandler(BoxBatteryLowException.class)
    public ResponseEntity<String> handleBoxBatteryLowException(BoxBatteryLowException boxBatteryLowException) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(boxBatteryLowException.getMessage());
    }

}
