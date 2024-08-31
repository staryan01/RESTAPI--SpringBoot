package com.jsp.restapi.helper;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class ResponseStructure<T>
{
	String message;
	T data;
}