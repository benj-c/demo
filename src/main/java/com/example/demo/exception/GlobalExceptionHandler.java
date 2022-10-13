package com.example.demo.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
@Slf4j
@Order(0)
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> errors = new ArrayList<>();
        ex.getBindingResult().getAllErrors().forEach(e -> {
            FieldError fe = (FieldError) e;
            String field = fe.getField();
            String msg = e.getDefaultMessage();
            errors.add(new StringBuilder().append(field).append(" > ").append(msg).toString());
        });
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(value = ApiException.class)
    public ResponseEntity<?> handleApiException(ApiException ex) {
        ResponseType responseType = ex.getResponseType();
        return ResponseEntity.status(responseType.getHttpStatus()).body(responseType);
    }

    @ExceptionHandler(value = BadCredentialsException.class)
    public ResponseEntity<?> handleBadCredentialsException(BadCredentialsException ex) {
        log.error("BadCredentialsException", ex);
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

    @ExceptionHandler(value = AccessDeniedException.class)
    public ResponseEntity<?> handleAccessDeniedException(AccessDeniedException ex) {
        log.error("AccessDeniedException", ex);
        return ResponseEntity.status(ResponseType.ACCESS_DENIED.getHttpStatus())
                .body(ResponseType.ACCESS_DENIED);
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<?> handleException(Exception ex) {
        log.error("InternalError", ex);
        return ResponseEntity.internalServerError().build();
    }
}
