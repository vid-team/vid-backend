package com.vid.vidbackend.global.error.handler;

import com.vid.vidbackend.global.error.exception.ApplicationException;
import com.vid.vidbackend.global.error.exception.InvalidValueException;
import com.vid.vidbackend.global.error.model.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@Slf4j
@RestControllerAdvice
public class GlobalCustomExceptionHandler {

    @ExceptionHandler(InvalidValueException.class)
    protected ResponseEntity<ErrorResponse> handleInvalidValueException(final InvalidValueException e) {
        log.error("handleInvalidValueException", e);
        final List<ErrorResponse.FieldError> fieldErrors = ErrorResponse.FieldError.of(e.getField(), e.getValue(), e.getMessage());
        final ErrorResponse response = ErrorResponse.of(e.getErrorCode(), fieldErrors);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatus()));
    }

    @ExceptionHandler(ApplicationException.class)
    protected ResponseEntity<ErrorResponse> handleApplicationException(final ApplicationException e) {
        log.error("handleApplicationException", e);
        final ErrorResponse response = ErrorResponse.of(e.getErrorCode());
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatus()));
    }
}
