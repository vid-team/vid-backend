package com.vid.vidbackend.global.error.handler;

import com.vid.vidbackend.global.error.model.ErrorCode;
import com.vid.vidbackend.global.error.model.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error("handleMethodArgumentNotValidException", e);
        final ErrorResponse response = ErrorResponse.of(ErrorCode.INVALID_INPUT_VALUE, e.getBindingResult());
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatus()));
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    protected ResponseEntity<ErrorResponse> handleHttpRequestMethodNotSupportedException(final HttpRequestMethodNotSupportedException e, HttpServletRequest request) {
        log.info("request = {}", request.getRequestURI());
        log.error("handleHttpRequestMethodNotSupportedException", e);
        final ErrorResponse response = ErrorResponse.of(ErrorCode.METHOD_NOT_SUPPORT);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatus()));
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ErrorResponse> handleException(final Exception e) {
        log.error("handleException", e);
        final ErrorResponse response = ErrorResponse.of(ErrorCode.SERVER_ERROR);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatus()));
    }
}
