package com.jsp.restapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title =  "Employee Crud",description =  "REST Api for Performing CRUD Operations on Employee Data" , version = "1.0" , contact = @Contact(email = "aryankumar0282000@gmail.com" , name="Aryan Kunwar" , url = "https://github.com/staryan01")))
public class SpringBootRestapi1Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootRestapi1Application.class, args);
	}
 
}
