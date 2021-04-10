package com.jonybuzz.encontralo.controller.advice;

import com.jonybuzz.encontralo.controller.error.ApiError;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ControllerRestAdvice {

  private final int ONE_ERROR = 1;

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
    if (errorList.size() > ONE_ERROR) {
      textError
          .append("Faltan completar los campos: ")
          .append(errorList.stream().sorted().collect(Collectors.joining(", ")));
    } else {
      textError
          .append("Faltan completar el campo ")
          .append(errorList.stream().findFirst().get());

    }
    return new ApiError(exception.getClass().getName(), textError.append(".").toString());
  }
}
