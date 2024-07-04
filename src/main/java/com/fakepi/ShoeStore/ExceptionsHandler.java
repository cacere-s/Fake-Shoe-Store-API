package com.fakepi.ShoeStore;

import com.fakepi.ShoeStore.Auth.AuthResponse;
import com.fakepi.ShoeStore.Excepcions.CategoryNotFoundException;
import com.fakepi.ShoeStore.Excepcions.ShoeNotFoundException;
import com.fakepi.ShoeStore.Excepcions.UserNotFoundException;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class ExceptionsHandler {

  @ResponseStatus(code = HttpStatus.BAD_REQUEST)
  @ExceptionHandler(value = MethodArgumentNotValidException.class)
  public  Map<String, String> handleValidateExceptionAuth(MethodArgumentNotValidException ex) {
    Map<String, String> errors = new HashMap<String, String>();
    ex.getBindingResult().getAllErrors().forEach(error -> {
      String fieldName = ((FieldError) error).getField();
      String message = error.getDefaultMessage();
      errors.put(fieldName, message);
    });
    return errors;
  }

  @ExceptionHandler(CategoryNotFoundException.class)
  public ResponseEntity<Object> handleCategoryNotFoundException(CategoryNotFoundException ex){
    return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(UserNotFoundException.class)
  public ResponseEntity<Object> handleUserNotFoundException(CategoryNotFoundException ex){
    return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(ShoeNotFoundException.class)
  public ResponseEntity<Object> handleShoeNotFoundException(ShoeNotFoundException ex){
    return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
  }
}
