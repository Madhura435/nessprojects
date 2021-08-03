package com.madhura.fullstackbackendness.ExceptionHandling;

import java.util.Date;
import java.util.Set;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.madhura.fullstackbackendness.exception.ResourceNotFoundException;
import com.madhura.fullstackbackendness.exception.WrongInputException;

@ControllerAdvice
public class GlobalExceptionHandling extends ResponseEntityExceptionHandler{

	@ExceptionHandler(ResourceNotFoundException.class)
public ResponseEntity<?> ResourceNotFoundHandling
(ResourceNotFoundException exception,WebRequest request)
{
	ExceptionDetails exceptionDetails=new ExceptionDetails(new Date(),exception.getMessage()+" ,please change your id",request.getDescription(false));
   return new ResponseEntity<>(exceptionDetails,HttpStatus.NOT_FOUND);
}

	@ExceptionHandler(WrongInputException.class)
public ResponseEntity<?> WrongInputHandling
(WrongInputException exception,WebRequest request)
{
	ExceptionDetails exceptionDetails=new ExceptionDetails(new Date(),exception.getMessage()+" ,please provide valid input details",request.getDescription(false));
   return new ResponseEntity<>(exceptionDetails,HttpStatus.NOT_FOUND);
}

	@Override
protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
		HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
	ExceptionDetails exceptionDetails=new ExceptionDetails(new Date(),ex.getMessage()+" ,Please change http method type",request.getDescription(false));
	return new ResponseEntity<>(exceptionDetails,HttpStatus.NOT_FOUND);
}

	@ExceptionHandler(Exception.class)
public ResponseEntity<?> globlalExceptionhandler(Exception exception,WebRequest request)
{
	ExceptionDetails exceptionDetails=new ExceptionDetails(new Date(),exception.getMessage()+" ,Something went rong from server we looking on it please wait ",request.getDescription(false));
	return new ResponseEntity<>(exceptionDetails,HttpStatus.INTERNAL_SERVER_ERROR);
}
	
}

