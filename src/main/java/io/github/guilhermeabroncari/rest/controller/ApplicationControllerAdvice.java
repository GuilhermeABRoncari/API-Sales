package io.github.guilhermeabroncari.rest.controller;

import io.github.guilhermeabroncari.exceptions.BusinessRuleException;
import io.github.guilhermeabroncari.rest.ApiErrors;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApplicationControllerAdvice {
    @ExceptionHandler(BusinessRuleException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrors BusinessRuleHandlerException(BusinessRuleException ex) {
        String errorMessage = ex.getMessage();
        return new ApiErrors(errorMessage);
    }
}
