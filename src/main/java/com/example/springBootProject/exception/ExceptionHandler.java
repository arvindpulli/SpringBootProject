package com.example.springBootProject.exception;

import java.util.HashMap;
import java.util.List;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.springBootProject.config.ResponseStructure;

import jakarta.validation.ConstraintViolation;

@RestControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
	
		List<ObjectError> list=ex.getAllErrors();
		HashMap<String, String> hashmap=new HashMap<String, String>();
		
		for(ObjectError error: list) {
			String message=error.getDefaultMessage();
			String fieldname=((FieldError)error).getField();
			
			hashmap.put(fieldname, message);
		}
		
		return new ResponseEntity<Object>(hashmap,HttpStatus.BAD_REQUEST);		
	}
	
	
	 
	@org.springframework.web.bind.annotation.ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> userNotPresent(UserNotFound ex){
		
		ResponseStructure<String> structure=new ResponseStructure<String>();
		structure.setData("method did not executed");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setMessage(ex.getMsg());
		
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
		
	}
	
	@org.springframework.web.bind.annotation.ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> MenuNotPresent(MenuNotFound ex){
			
			ResponseStructure<String> structure=new ResponseStructure<String>();
			structure.setData("method did not executed");
			structure.setStatus(HttpStatus.NOT_FOUND.value());
			structure.setMessage(ex.getMsg());
			
			return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
			
		}
	
	@org.springframework.web.bind.annotation.ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> ProductNotPresent(ProductNotFound ex){
			
			ResponseStructure<String> structure=new ResponseStructure<String>();
			structure.setData("method did not executed");
			structure.setStatus(HttpStatus.NOT_FOUND.value());
			structure.setMessage(ex.getMsg());
			
			return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
			
		}

	@org.springframework.web.bind.annotation.ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> ItemNotPresent(ItemNotFound ex){
			
			ResponseStructure<String> structure=new ResponseStructure<String>();
			structure.setData("method did not executed");
			structure.setStatus(HttpStatus.NOT_FOUND.value());
			structure.setMessage(ex.getMsg());
			
			return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
			
		}
	
	@org.springframework.web.bind.annotation.ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> PasswordNotMatch(PasswordNotMatch ex){
	
		ResponseStructure<String> structure=new ResponseStructure<String>();
		structure.setData("Login Faild! Entered Password is Incorrect");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setMessage(ex.getMsg());
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
	}
	
	
	
	@org.springframework.web.bind.annotation.ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> FoodOrderNotPresent(FoodOrderNotFound ex){
			
			ResponseStructure<String> structure=new ResponseStructure<String>();
			structure.setData("method did not executed");
			structure.setStatus(HttpStatus.NOT_FOUND.value());
			structure.setMessage(ex.getMsg());
			
			return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
			
		}
	
	@org.springframework.web.bind.annotation.ExceptionHandler
	public ResponseEntity<Object> handleConstrainViolationException(jakarta.validation.ConstraintViolationException ex){
		
		ResponseStructure<Object> structure=new ResponseStructure<Object>();
		HashMap<String, String> hashmap=new HashMap<String, String>();
		
		for(ConstraintViolation<?> violation:ex.getConstraintViolations()) {
			String field=violation.getPropertyPath().toString();
			String message=violation.getMessage();
			hashmap.put(field, message);
		}
		
		structure.setMessage("Add proper details");
		structure.setData(hashmap);
		structure.setStatus(HttpStatus.FORBIDDEN.value());
		
		return new ResponseEntity<Object>(structure,HttpStatus.BAD_REQUEST);
	}

	
	
	
	
	

}
