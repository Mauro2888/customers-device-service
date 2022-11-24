package com.customer.service.exception.advice;

import com.customer.service.exception.CustomerMaxDeviceException;
import com.customer.service.exception.CustomerNotFoundException;
import com.customer.service.exception.UserNotAvailableException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;

@RestControllerAdvice
public class CustomerAdviceController {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<Object> handleMethodArgumentNotValid(ServletWebRequest webRequest,MethodArgumentNotValidException ex) {
        String errorMessage = ex.getBindingResult().getFieldErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .findFirst()
                .orElse(ex.getMessage());

        ApiError apiError = new ApiError(
                HttpStatus.BAD_REQUEST,
                webRequest.getDescription(true),
                errorMessage,
                LocalDateTime.now());

        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiError> handleConstraint(ServletWebRequest webRequest,ConstraintViolationException ex) {
        ApiError apiError = new ApiError(
                HttpStatus.BAD_REQUEST,
                webRequest.getDescription(true),
                ex.getConstraintViolations().stream().map(ConstraintViolation::getMessage).findFirst()
                        .orElse(ex.getMessage()),
                LocalDateTime.now());
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CustomerMaxDeviceException.class)
    public ResponseEntity<ApiError> handleConstraint(ServletWebRequest webRequest,CustomerMaxDeviceException ex) {
        ApiError apiError = new ApiError(
                HttpStatus.BAD_REQUEST,
                webRequest.getDescription(true),
                ex.getMessage(),
                LocalDateTime.now());
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<ApiError> handleNotFound(ServletWebRequest webRequest,CustomerNotFoundException ex) {
        ApiError apiError = new ApiError(
                HttpStatus.NOT_FOUND,
                webRequest.getDescription(true),
                ex.getLocalizedMessage(),
                LocalDateTime.now());
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserNotAvailableException.class)
    public ResponseEntity<ApiError> handleNotAvailable(ServletWebRequest webRequest,UserNotAvailableException ex) {
        ApiError apiError = new ApiError(
                HttpStatus.CONFLICT,
                webRequest.getDescription(true),
                ex.getLocalizedMessage(),
                LocalDateTime.now());
        return new ResponseEntity<>(apiError, HttpStatus.CONFLICT);
    }

}
