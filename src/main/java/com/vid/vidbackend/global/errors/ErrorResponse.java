package com.vid.vidbackend.global.errors;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
@Setter
public class ErrorResponse {

    private LocalDateTime timestamp;
    private int code;
    private String status;
    private String message;
    private String stackTrace;
    private Object data;

    private ErrorResponse() {
        timestamp = LocalDateTime.now();
    }

    private ErrorResponse(final HttpStatus httpStatus, final String message) {
        this();
        this.code = httpStatus.value();
        this.status = httpStatus.name();
        this.message = message;
    }

    private ErrorResponse(final HttpStatus httpStatus, final String message, final String stackTrace) {
        this(httpStatus, message);
        this.stackTrace = stackTrace;
    }

    private ErrorResponse(final HttpStatus httpStatus, final String message, final String stackTrace, final Object data) {
        this(httpStatus, message, stackTrace);
        this.data = data;
    }

    public static ErrorResponse of(final HttpStatus httpStatus, final String message) {
        return new ErrorResponse(httpStatus, message);
    }

    public static ErrorResponse of(final HttpStatus httpStatus, final String message, final String stackTrace) {
        return new ErrorResponse(httpStatus, message, stackTrace);
    }

    public static ErrorResponse of(final HttpStatus httpStatus, final String message, final String stackTrace, final Object data) {
        return new ErrorResponse(httpStatus, message, stackTrace, data);
    }
}
