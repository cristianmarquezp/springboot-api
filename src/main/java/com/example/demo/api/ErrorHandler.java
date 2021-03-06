package com.example.demo.api;


import com.example.demo.exception.ApplicationErrors;
import com.example.demo.exception.CustomerNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class ErrorHandler extends ResponseEntityExceptionHandler {
    @Value("${api_doc_url}")
    private String details;

    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<ApplicationErrors> handleCustomerNotFoundException(CustomerNotFoundException exception, WebRequest webRequest){
        ApplicationErrors error = new ApplicationErrors();
        error.setCode(101);
        error.getMessage(exception.getMessage());
        error.setDetails(details);
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}
