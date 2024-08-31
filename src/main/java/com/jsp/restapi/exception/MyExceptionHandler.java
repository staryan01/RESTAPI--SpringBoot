package com.jsp.restapi.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.jsp.restapi.dto.Employee;
import com.jsp.restapi.helper.ResponseStructure;

@RestControllerAdvice
public class MyExceptionHandler 
{
	@Autowired
	ResponseStructure<String> structure;
	
	@ExceptionHandler(DataNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> handle(DataNotFoundException exception)
	{
		structure.setMessage("Data not found");
		structure.setData(exception.getMessage());
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
	}
	
	
	@ExceptionHandler(DataExistException.class)
	public ResponseEntity<ResponseStructure<String>> handle(DataExistException exception)
	{
		structure.setMessage("Data Already Exist");
		structure.setData(exception.getMessage());
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.BAD_REQUEST);
	}
	
}
