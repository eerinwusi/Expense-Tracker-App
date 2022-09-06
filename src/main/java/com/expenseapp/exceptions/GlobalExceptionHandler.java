package com.expenseapp.exceptions;

import com.expenseapp.model.ErrorObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

//    handles exceptions such as inputing an id that does not exist in the db.
    @ExceptionHandler(ExpenseNotFoundException.class)
    public ResponseEntity<ErrorObject> handleExpenseNotFondException(ExpenseNotFoundException ex, WebRequest request) {

        ErrorObject errorObject = new ErrorObject();

        errorObject.setStatusCode(HttpStatus.NOT_FOUND.value());

        errorObject.setMessage(ex.getMessage());

        errorObject.setTimeStamp(new Date());

        return new ResponseEntity<ErrorObject>(errorObject, HttpStatus.NOT_FOUND);
    }
    //    this handles exceptions such as putting a string in place of an integer for an id
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorObject> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex, WebRequest request) {

        ErrorObject errorObject = new ErrorObject();

        errorObject.setStatusCode(HttpStatus.BAD_REQUEST.value());

        errorObject.setMessage(ex.getMessage());

        errorObject.setTimeStamp(new Date());

        return new ResponseEntity<ErrorObject>(errorObject, HttpStatus.BAD_REQUEST);
    }

//    handles exceptions such as stack overflow error
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorObject> handleGeneralException(Exception ex, WebRequest request) {

        ErrorObject errorObject = new ErrorObject();

        errorObject.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());

        errorObject.setMessage(ex.getMessage());

        errorObject.setTimeStamp(new Date());

        return new ResponseEntity<ErrorObject>(errorObject, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders httpHeaders,
    HttpStatus httpStatus, WebRequest request) {
        Map<String, Object> body = new HashMap<String, Object>();

        body.put("timeStamp", new Date());

        body.put("statusCode", HttpStatus.BAD_REQUEST.value());

        List<String> errors = ex.getBindingResult()
                        .getFieldErrors().stream().map(x -> x.getDefaultMessage()).collect(Collectors.toList());

        body.put("message", errors);

        return new ResponseEntity<Object>(body, HttpStatus.BAD_REQUEST);
    }
}
