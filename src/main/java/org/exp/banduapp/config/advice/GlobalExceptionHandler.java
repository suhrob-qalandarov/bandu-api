package org.exp.banduapp.config.advice;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.servlet.NoHandlerFoundException;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    // 1. Entity topilmadi
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorRes> handleNotFound(EntityNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorRes("NOT_FOUND", ex.getMessage()));
    }

    // 2. Validation xatolari (DTO)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorRes> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = error instanceof FieldError ? ((FieldError) error).getField() : error.getObjectName();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        String message = errors.entrySet().stream()
                .map(entry -> entry.getKey() + ": " + entry.getValue())
                .findFirst()
                .orElse("Validation failed");

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorRes("VALIDATION_ERROR", message, errors));
    }

    // 3. Biznes logika xatolari (RuntimeException)
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorRes> handleRuntimeException(RuntimeException ex) {
        log.error("Business logic error: {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorRes("BAD_REQUEST", ex.getMessage()));
    }

    // 4. Illegal argument
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorRes> handleIllegalArgument(IllegalArgumentException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorRes("INVALID_INPUT", ex.getMessage()));
    }

    // 5. Illegal state
    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<ErrorRes> handleIllegalState(IllegalStateException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new ErrorRes("CONFLICT", ex.getMessage()));
    }

    // 6. Umumiy xato
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorRes> handleAll(Exception ex) {
        log.error("Unexpected error", ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorRes("INTERNAL_ERROR", "Tizimda xatolik yuz berdi. Iltimos, keyinroq urinib ko'ring."));
    }

    // 6. Ruxsat xato
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorRes> handleAccessDenied(AccessDeniedException ex) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(new ErrorRes("FORBIDDEN", "Sizga ushbu resursga kirish taqiqlangan"));
    }

    // 7. 404 - Endpoint topilmadi
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ErrorRes> handleNotFound(NoHandlerFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorRes("ENDPOINT_NOT_FOUND", "So'ralgan manzil topilmadi: " + ex.getRequestURL()));
    }

    // 8. 405 - Noto'g'ri HTTP method
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ErrorRes> handleMethodNotAllowed(HttpRequestMethodNotSupportedException ex) {
        String supported = ex.getSupportedHttpMethods() != null
                ? ex.getSupportedHttpMethods().toString()
                : "[]";
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED)
                .body(new ErrorRes(
                        "METHOD_NOT_ALLOWED",
                        "Bu endpoint uchun " + ex.getMethod() + " metodi qo'llab-quvvatlanmaydi. Qo'llab-quvvatlanadigan metodlar: " + supported
                ));
    }
}