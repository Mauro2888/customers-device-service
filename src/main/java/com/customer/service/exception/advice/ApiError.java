package com.customer.service.exception.advice;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public record ApiError(HttpStatus status,
                       String path,
                       String message,
                       @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
                       LocalDateTime timestamp) {}
