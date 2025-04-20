package com.viktor.oop.car.parts.shop.config.exception.handler;

import com.viktor.oop.car.parts.shop.config.exception.BrandNotFoundException;
import com.viktor.oop.car.parts.shop.config.exception.CarNotFoundException;
import com.viktor.oop.car.parts.shop.config.exception.ManufacturerNotFoundException;
import com.viktor.oop.car.parts.shop.config.exception.PartNotFoundException;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGlobalException(Exception ex) {
        var status = HttpStatus.INTERNAL_SERVER_ERROR;
        LoggerFactory.getLogger(GlobalExceptionHandler.class).error(ex.getMessage(), ex);
        return ResponseEntity.status(status).body(new ErrorResponse(status, "Oopsie, something went wrong :("));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        var status = HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(new ErrorResponse(status, ex.getBindingResult().getAllErrors().getFirst().getDefaultMessage()));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        var status = HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(new ErrorResponse(status, ex.getMessage()));
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ErrorResponse> handleNoResourceFoundException(NoResourceFoundException ex) {
        var status = HttpStatus.NOT_FOUND;
        return ResponseEntity.status(status).body(new ErrorResponse(status, ex.getMessage()));
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ErrorResponse> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex) {
        var status = HttpStatus.METHOD_NOT_ALLOWED;
        return ResponseEntity.status(status).body(new ErrorResponse(status, ex.getMessage()));
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
        var status = HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(new ErrorResponse(status, ex.getMessage()));
    }

    // Custom exceptions

    @ExceptionHandler(CarNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleCarNotFoundException(CarNotFoundException ex) {
        var status = HttpStatus.NOT_FOUND;
        return ResponseEntity.status(status).body(new ErrorResponse(status, ex.getMessage()));
    }
    @ExceptionHandler(PartNotFoundException.class)
    public ResponseEntity<ErrorResponse> handlePartNotFoundException(PartNotFoundException ex) {
        var status = HttpStatus.NOT_FOUND;
        return ResponseEntity.status(status).body(new ErrorResponse(status, ex.getMessage()));
    }

    @ExceptionHandler(ManufacturerNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleManufacturerNotFoundException(ManufacturerNotFoundException ex) {
        var status = HttpStatus.NOT_FOUND;
        return ResponseEntity.status(status).body(new ErrorResponse(status, ex.getMessage()));
    }

    @ExceptionHandler(BrandNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleBrandNotFoundException(BrandNotFoundException ex) {
        var status = HttpStatus.NOT_FOUND;
        return ResponseEntity.status(status).body(new ErrorResponse(status, ex.getMessage()));
    }
}
