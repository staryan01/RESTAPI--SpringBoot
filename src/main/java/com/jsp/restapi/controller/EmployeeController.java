package com.jsp.restapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.restapi.dto.Employee;
import com.jsp.restapi.helper.ResponseStructure;
import com.jsp.restapi.repository.EmployeeRepository;
import com.jsp.restapi.service.EmployeeService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/v1")
public class EmployeeController 
{
	
	@Autowired
	EmployeeService service;
	
	
//  Save one employee
	@Operation(summary = "Save one employee")
	@PostMapping("/employees")
	public ResponseEntity<ResponseStructure<Employee>> saveEmployee(@RequestBody Employee employee)
	{
		return service.save(employee);
	}

	
	
//  find one employee
	@Operation(summary = "find one employee by id")
	@GetMapping("/employees/{id}")
	public ResponseEntity<ResponseStructure<Employee>> fetchOneEmployee(@PathVariable int id )
	{
		return service.fetchById(id);
	}




	
//	find all employee
	@GetMapping("/employees")
	public ResponseEntity<ResponseStructure<List<Employee>>> fetchAllEmployee()
	{
		return service.fetchAll();
	}



	
	
//	save multiple employee
	@PostMapping("/employees/many")
	public ResponseEntity<ResponseStructure<List<Employee>>> saveAllEmployee(@RequestBody List<Employee> employees)
	{
		return service.saveAll(employees);
	
	}



	
	
//	Fetch by name
	@GetMapping("/employees/name/{name}")
	public ResponseEntity<ResponseStructure<List<Employee>>> fetchEmployeeByName(@PathVariable String name )
	{
		return service.fetchByName(name);
	}




	
//	fetch by salary
	@GetMapping("/employees/salary/{salary}")
	public ResponseEntity<ResponseStructure<List<Employee>>> fetchBySalary(@PathVariable double salary )
	{
		return service.fetchEmployeeBySalary(salary);
	}



	
	
//	fetch by Mobile number
	@GetMapping("/employees/mobile/{mobile}")
	public ResponseEntity<ResponseStructure<Employee>> fetchEmployeeByMobile(@PathVariable long mobile )
	{
		return service.fetchByMobile(mobile);
	}




	
//	Delete by id
	@DeleteMapping("/employees/{id}")
	public ResponseEntity<ResponseStructure<Employee>> deleteEmployeeById(@PathVariable int id )
	{
		return service.deleteById(id);

	}
	
//	update data all data we use put
	@PutMapping("/employees")
	public ResponseEntity<ResponseStructure<Employee>> updateEmployee(@RequestBody Employee employee)
	{
		return service.update(employee);
	}
	
//	update data one by one using 
	@PatchMapping("/employees/{id}")
	public ResponseEntity<ResponseStructure<Employee>> updateEmployee(@RequestBody Employee employee ,@PathVariable int id)
	{
		return service.update(employee,id);
	}





}
