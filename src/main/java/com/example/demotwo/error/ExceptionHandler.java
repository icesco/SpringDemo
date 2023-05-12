package com.example.demotwo.error;

import lombok.extern.slf4j.Slf4j;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@ControllerAdvice
class MyExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UserException.UserNotFoundException.class)
    ProblemDetail handleGeneric(Exception exception) {
        log.error("UserNotFoundException", exception);
        var problemDetail =
                ProblemDetail.forStatusAndDetail(
                        HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.getReasonPhrase());
        addCustomInfo(problemDetail, ApplicationErrorCode.USER_NOT_FOUND);

        return problemDetail;
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ve,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request) {

        var problemDetail = ve.getBody();
        var fieldError = ve.getFieldError();
        if (fieldError != null) {
            problemDetail.setDetail(
                    "'%s' %s".formatted(fieldError.getField(), fieldError.getDefaultMessage()));
        }
        addCustomInfo(problemDetail, ApplicationErrorCode.VALIDATION);

        log.error("Validation error", ve);
        return super.handleExceptionInternal(ve, problemDetail, headers, status, request);
    }

    @Override
    protected ProblemDetail createProblemDetail(
            Exception ex,
            HttpStatusCode status,
            String defaultDetail,
            String detailMessageCode,
            Object[] detailMessageArguments,
            WebRequest request) {
        var problemDetail =
                super.createProblemDetail(
                        ex, status, defaultDetail, detailMessageCode, detailMessageArguments, request);
        log.error(ex.getMessage(), ex);
        addCustomInfo(problemDetail, ApplicationErrorCode.INTERNAL);

        return problemDetail;
    }

    private void addCustomInfo(
            @NotNull ProblemDetail problemDetail,
            @NotNull ApplicationErrorCode errorCode) {
        problemDetail.setProperty("code", errorCode.getCode());
    }

}
