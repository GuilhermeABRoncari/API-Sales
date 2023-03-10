package io.github.guilhermeabroncari.rest.controller;

import io.github.guilhermeabroncari.exceptions.BusinessRuleException;
import io.github.guilhermeabroncari.exceptions.RequestNotFoundException;
import io.github.guilhermeabroncari.rest.ApiErrors;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@RestControllerAdvice
public class ApplicationControllerAdvice {
    @ExceptionHandler(BusinessRuleException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrors BusinessRuleHandlerException(BusinessRuleException ex) {
        return new ApiErrors(ex.getMessage());
    }

    @ExceptionHandler(RequestNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiErrors RequestNotFundHandlerException(RequestNotFoundException ex) {
        return new ApiErrors(ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrors methodNotValidHandlerException(MethodArgumentNotValidException ex) {
        return new ApiErrors(ex.getBindingResult().getAllErrors().stream()
                .map(objectError -> objectError.getDefaultMessage()).collect(Collectors.toList()));
    }
}
