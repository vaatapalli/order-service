package com.orderservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class OrderServiceException {

    @ExceptionHandler(OrderNotFoundExcpetion.class)
    protected ResponseEntity<ErrorDetails> oderNotFound(OrderNotFoundExcpetion ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setTimestamp(new Date());
        errorDetails.setMessage(ex.getMessage());
        errorDetails.setDetails(request.getDescription(false));
        errorDetails.setError("NOT_FOUND");
        errorDetails.setStatus(HttpStatus.NOT_FOUND.value());

        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ItemNotFoundExcpetion.class)
    protected ResponseEntity<ErrorDetails> itemNotFound(ItemNotFoundExcpetion ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setTimestamp(new Date());
        errorDetails.setMessage(ex.getMessage());
        errorDetails.setDetails(request.getDescription(false));
        errorDetails.setError("NOT_FOUND");
        errorDetails.setStatus(HttpStatus.NOT_FOUND.value());

        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ResponseEntity<ErrorDetails> inputValidation(MethodArgumentNotValidException ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setTimestamp(new Date());
        errorDetails.setMessage(ex.getBindingResult().getFieldErrors().get(0).getDefaultMessage());
        errorDetails.setDetails(request.getDescription(false));
        errorDetails.setError("BAD_REQUEST");
        errorDetails.setStatus(HttpStatus.BAD_REQUEST.value());

        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ErrorDetails> handleAllExceptions(Exception ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setTimestamp(new Date());
        errorDetails.setMessage(ex.getMessage());
        errorDetails.setDetails(request.getDescription(false));
        errorDetails.setError("INTERNAL_SERVER_ERROR");
        errorDetails.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());

        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ServerNotFoundException.class)
    public final ResponseEntity<ErrorDetails> handleServerNotFoundExceptions(Exception ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setTimestamp(new Date());
        errorDetails.setMessage(ex.getMessage());
        errorDetails.setDetails(request.getDescription(false));
        errorDetails.setError("SERVICE_UNAVAILABLE");
        errorDetails.setStatus(HttpStatus.SERVICE_UNAVAILABLE.value());

        return new ResponseEntity<>(errorDetails, HttpStatus.SERVICE_UNAVAILABLE);
    }

}

