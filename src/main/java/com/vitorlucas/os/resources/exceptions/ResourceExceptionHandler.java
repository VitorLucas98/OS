package com.vitorlucas.os.resources.exceptions;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.vitorlucas.os.services.exceptions.ObjectNotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request){
		StandardError error = new StandardError(LocalDateTime.now(), 
				HttpStatus.NOT_FOUND.value(), "Objeto não encontrado !" , e.getMessage(), request.getRequestURI());
	return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ValidationError> validation(MethodArgumentNotValidException e, HttpServletRequest request){
		ValidationError error = new ValidationError(LocalDateTime.now(), 
				HttpStatus.BAD_REQUEST.value(), "Erro de validação" , e.getMessage(), request.getRequestURI());
		
		for (FieldError f : e.getBindingResult().getFieldErrors()) {
			error.addList(f.getField(), f.getDefaultMessage());
		}	
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}
	
	
	
	
}
