package com.example.demotwo.error;

import lombok.extern.slf4j.Slf4j;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@ControllerAdvice
class MyExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UserException.UserNotFoundException.class)
    ProblemDetail handleNotFoundUser(Exception exception) {
        log.error("UserNotFoundException", exception);
        var problemDetail =
                ProblemDetail.forStatusAndDetail(
                        HttpStatus.NOT_FOUND,
                        HttpStatus.NOT_FOUND.getReasonPhrase());
        addCustomInfo(problemDetail, ApplicationErrorCode.USER_NOT_FOUND);

        return problemDetail;
    }

    @ExceptionHandler(UserException.UserAlreadyExistsException.class)
    ProblemDetail handleUserAlreadyExists(Exception exception) {
        log.error("UserAlreadyExistsException", exception);
        var problemDetail =
                ProblemDetail.forStatusAndDetail(
                        HttpStatus.FOUND,
                        HttpStatus.FOUND.getReasonPhrase());
        addCustomInfo(problemDetail, ApplicationErrorCode.USER_ALREADY_EXISTS);

        return problemDetail;
    }


    private void addCustomInfo(
            @NotNull ProblemDetail problemDetail,
            @NotNull ApplicationErrorCode errorCode) {
        problemDetail.setProperty("code", errorCode.getCode());
    }

}
