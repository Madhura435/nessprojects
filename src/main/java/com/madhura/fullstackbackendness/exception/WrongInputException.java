package com.madhura.fullstackbackendness.exception;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WrongInputException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	public String message;
	
}
