package test.example.first.testspringbootfirst.exceptions;


import io.sentry.Sentry;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import test.example.first.testspringbootfirst.helper.CustomResponse;

import java.util.Date;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<CustomResponse> specificCustomExceptionResourceNotFoundException(
            ResourceNotFoundException exception, WebRequest webRequest) {
        CustomResponse customErrorResponse = new CustomResponse();
        customErrorResponse.setMessage(exception.getMessage());
        customErrorResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
        customErrorResponse.setTimestamps(new Date().toString());
        Sentry.captureException(exception);

        return new ResponseEntity<>(customErrorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<CustomResponse> CustomExceptionResourceNotFoundException(
            Exception exception, WebRequest webRequest) {
        CustomResponse customErrorResponse = new CustomResponse();
        customErrorResponse.setMessage(exception.getCause() +" \n "+exception.toString());
        customErrorResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
        customErrorResponse.setTimestamps(new Date().toString());
        Sentry.captureException(exception);
        return new ResponseEntity<>(customErrorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(HttpClientErrorException.BadRequest.class)
    public ResponseEntity<String> handleBadRequest(HttpClientErrorException.BadRequest ex) {
        // Log the error to Sentry
        Sentry.captureException(ex);

        // Return an error response to the client
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Bad Request: " + ex.getMessage());
    }
}
