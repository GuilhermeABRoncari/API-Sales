package io.github.guilhermeabroncari.rest;

import lombok.Data;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;


public class ApiErrors {
    @Getter
    private List<String> error;

    public ApiErrors(List<String> error) {
        this.error = error;
    }

    public ApiErrors(String errorMessage) {
        this.error = Arrays.asList(errorMessage);
    }

}
