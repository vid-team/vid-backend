package com.vid.vidbackend.global.errors;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ErrorResponse {

    private String code;
    private String status;
    private String message;
    private List<FieldError> errors;

    private ErrorResponse(final ErrorCode code) {
        this.code = code.getCode();
        this.status = code.getStatus();
        this.message = code.getMessage();
    }

    private ErrorResponse(final ErrorCode code, final List<FieldError> errors) {
        this(code);
        this.errors = errors;
    }

    public static ErrorResponse of(final ErrorCode code) {
        return new ErrorResponse(code);
    }

    public static ErrorResponse of(final ErrorCode code, List<FieldError> errors) {
        return new ErrorResponse(code, errors);
    }

    public static ErrorResponse of(final ErrorCode code, BindingResult bindingResult) {
        return new ErrorResponse(code, FieldError.of(bindingResult));
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class FieldError {
        private String field;
        private String value;
        private String reason;

        private FieldError(final String field, final String value, final String reason) {
            this.field = field;
            this.value = value;
            this.reason = reason;
        }

        public static List<FieldError> of(final String field, final  String value, final String reason) {
            return List.of(new FieldError(field, value, reason));
        }

        public static List<FieldError> of(final BindingResult bindingResult) {
            return bindingResult.getFieldErrors().stream()
                    .map(error -> new FieldError(
                            error.getField(),
                            error.getRejectedValue() == null ? "" : error.getRejectedValue().toString(),
                            error.getDefaultMessage()))
                    .collect(Collectors.toList());
        }
    }
}
