package io.github.odeivissonsantos.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Getter
@AllArgsConstructor
public class ApiErrors {

    private final List<String> errors;

    public ApiErrors(String message) {
        this.errors = Arrays.asList(message);
    }
}
