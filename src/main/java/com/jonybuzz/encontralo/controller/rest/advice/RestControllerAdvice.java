package com.jonybuzz.encontralo.controller.rest.advice;

import com.jonybuzz.encontralo.controller.error.ApiError;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.stream.Collectors;

@org.springframework.web.bind.annotation.RestControllerAdvice
public class RestControllerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError illegalArgumentExceptionHandler(MethodArgumentNotValidException exception) {
        List<String> errorList = exception
                .getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());
        StringBuilder textError = new StringBuilder();
        if (errorList.size() > 1) {
            textError
                    .append("Faltan completar los campos: ")
                    .append(errorList.stream().sorted().collect(Collectors.joining(", ")));
        } else {
            textError
                    .append("Falta completar el campo ")
                    .append(errorList.stream().findFirst().get());

        }
        return new ApiError(exception.getClass().getName(), textError.append(".").toString());
    }
}
