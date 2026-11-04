package org.example.minibank;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler { //отловщик ошибок по всему приложению

    @ExceptionHandler(HandlerMethodValidationException.class) //ловит ошибки валидации(не все)
    public ResponseEntity<Map<String, String>> handleMethodValidationExceptions(HandlerMethodValidationException ex){
        Map<String, String> response = new HashMap<>();
        response.put("status", "error");
        response.put("message", "Ошибка валидации: параметры запроса не прошли проверку");

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST); //статус 400
    }

    @ExceptionHandler(IllegalArgumentException.class) //ловит ошибки бизнес логики
    public ResponseEntity<Map<String, String>> handleBusinessException(IllegalArgumentException ex){
        Map<String, String> response = new HashMap<>();
        response.put("status", "error");
        response.put("message", ex.getMessage());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST); //статус 400
    }

    @ExceptionHandler(jakarta.validation.ConstraintViolationException.class)
    public ResponseEntity<Map<String, String>> handleConstraintViolationException(jakarta.validation.ConstraintViolationException ex){
        Map<String, String> response = new HashMap<>();
        response.put("status", "error");
        response.put("message", "Ошибка валидации: " + ex.getMessage());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
