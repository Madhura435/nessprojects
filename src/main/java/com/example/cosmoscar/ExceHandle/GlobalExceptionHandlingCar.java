package com.example.cosmoscar.ExceHandle;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.cosmoscar.Exception.ResourceNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandlingCar extends ResponseEntityExceptionHandler {

@ExceptionHandler(ResourceNotFoundException.class)
public ResponseEntity<?> ResourceNotFoundHandling
(ResourceNotFoundException exception,WebRequest request)
{
	ExceptionDetails exceptionDetails=new 
		ExceptionDetails(new Date(),exception.getMessage()+" ,please change your id and colour",request.getDescription(false));
  
	return new ResponseEntity<>(exceptionDetails,HttpStatus.NOT_FOUND);
}


@ExceptionHandler(NullPointerException.class)
public ResponseEntity<?> WrongInputHandling(NullPointerException exception,WebRequest request)
{
	
	ExceptionDetails exceptionDetails=
			new ExceptionDetails(new Date(),exception.getMessage()+" ,please provide valid input details",request.getDescription(false));
   
	return new ResponseEntity<>(exceptionDetails,HttpStatus.NOT_FOUND);
}

@Override
protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
		HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
	
	ExceptionDetails exceptionDetails=
			new ExceptionDetails(new Date(),ex.getMessage()+" ,Please change http method type",request.getDescription(false));
	
	return new ResponseEntity<>(exceptionDetails,HttpStatus.NOT_FOUND);
}


@ExceptionHandler(Exception.class)
public ResponseEntity<?> globlalExceptionhandler(Exception exception,WebRequest request)
{
	
	ExceptionDetails exceptionDetails=
			new ExceptionDetails(new Date(),exception.getMessage()+" ,Something went rong from server we looking on it please wait ",request.getDescription(false));
	
	return new ResponseEntity<>(exceptionDetails,HttpStatus.INTERNAL_SERVER_ERROR);
}


}
