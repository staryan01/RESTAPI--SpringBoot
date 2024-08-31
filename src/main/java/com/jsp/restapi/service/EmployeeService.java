package com.jsp.restapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.restapi.dto.Employee;
import com.jsp.restapi.exception.DataExistException;
import com.jsp.restapi.exception.DataNotFoundException;
import com.jsp.restapi.helper.ResponseStructure;
import com.jsp.restapi.repository.EmployeeRepository;

@Service
public class EmployeeService 
{
	@Autowired
	EmployeeRepository repository;
	
	@Autowired
	ResponseStructure<Employee> response1;
	
	@Autowired
	ResponseStructure<List<Employee>> response2;

	
//  Save one employee
	public ResponseEntity<ResponseStructure<Employee>> save(Employee employee) {
		if(repository.existsByMobile(employee.getMobile()))
		{
			throw new DataExistException("Mobile no is repeated "+ employee.getMobile());
		}
		else
		{
			repository.save(employee);
			response1.setMessage("Data Saved Successfully");
			response1.setData(employee);
			return	new ResponseEntity<ResponseStructure<Employee>>(response1,HttpStatus.CREATED);
		}
	}
	
//  find one employee
	public ResponseEntity<ResponseStructure<Employee>> fetchById(int id) {
		Employee employee=repository.findById(id).orElseThrow(() -> new DataNotFoundException("Data not found with id "+ id));
		
		
		{
			response1.setMessage("Data Found");
			response1.setData(employee);
			return	new ResponseEntity<ResponseStructure<Employee>>(response1,HttpStatus.FOUND);
		}
	}
	
//	fetch all employee
	public ResponseEntity<ResponseStructure<List<Employee>>> fetchAll() {
		List<Employee> list=repository.findAll();
		if(list.isEmpty())
		{
			throw new DataNotFoundException();
			
		}
		else
		{
			response2.setMessage("Data Found");
			response2.setData(list);
			return	new ResponseEntity<ResponseStructure<List<Employee>>>(response2,HttpStatus.FOUND);
		}
	}
	
//	save multiple employee
	public ResponseEntity<ResponseStructure<List<Employee>>> saveAll(List<Employee> employees) {
		for(Employee employee:employees)
		{
			if(repository.existsByMobile(employee.getMobile()))
			{
				throw new DataExistException("Data already exist with mobile no "+ employee.getMobile());
			}
		}
			
			
				repository.saveAll(employees);
				
				response2.setMessage("Data Saved Successfully");
				response2.setData(employees);
				return	new ResponseEntity<ResponseStructure<List<Employee>>>(response2,HttpStatus.CREATED);
	}
	
	
//	fetch by name
	public ResponseEntity<ResponseStructure<List<Employee>>> fetchByName(String name) {
		List<Employee> list=repository.findByName(name);
		if(list.isEmpty())
		{
			throw new DataNotFoundException("Data not found with name : "+ name);
		}
		else
		{
			response2.setMessage("Data Found");
			response2.setData(list);
			return	new ResponseEntity<ResponseStructure<List<Employee>>>(response2,HttpStatus.FOUND);
		}
	}
	
//	fetch by salary
	public ResponseEntity<ResponseStructure<List<Employee>>> fetchEmployeeBySalary(double salary) {
		List<Employee> list=repository.findBySalary(salary);
		if(list.isEmpty())
		{
			throw new DataNotFoundException("Data not found with salary : "+ salary);

		}
		else
		{
			response2.setMessage("Data Found");
			response2.setData(list);
			return	new ResponseEntity<ResponseStructure<List<Employee>>>(response2,HttpStatus.FOUND);
		}
	}
//	fetch by mobile
	public ResponseEntity<ResponseStructure<Employee>> fetchByMobile(long mobile) {
		Employee employee=repository.findByMobile(mobile);
		if(employee==null)
		{
			throw new DataNotFoundException("Data not found with mobile no : "+ mobile);

		}
		else
		{
			response1.setMessage("Data Found");
			response1.setData(employee);
			return	new ResponseEntity<>(response1,HttpStatus.FOUND);
		}
	}
//	delete by id
	public ResponseEntity<ResponseStructure<Employee>> deleteById(int id) {
		Employee employee=repository.findById(id).orElseThrow(() ->new DataNotFoundException("Data not found with id : "+ id));
		
		
		{	repository.deleteById(id);
			response1.setMessage("Data Deleted");
			response1.setData(employee);
			return	new ResponseEntity<ResponseStructure<Employee>>(response1,HttpStatus.OK);
		}
	}

	public ResponseEntity<ResponseStructure<Employee>> update(Employee employee) 
	{
		repository.save(employee);
		response1.setMessage("Data Updated successfully ");
		response1.setData(employee);
		return new ResponseEntity<ResponseStructure<Employee>>(response1,HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<Employee>> update(Employee employee, int id)
	{
		Employee	emp=repository.findById(id).orElseThrow(()-> new DataNotFoundException());
		
		if(employee.getName()!=null)
		{
			emp.setName(employee.getName());
		}
		if(employee.getMobile()!=0)
		{
			emp.setMobile(employee.getMobile());
		}
		if(employee.getSalary()!=0)
		{
			emp.setSalary(employee.getSalary());
		}
		
		repository.save(emp);
		response1.setMessage("Data updated successfully");
		response1.setData(emp);
		return new ResponseEntity<ResponseStructure<Employee>>(response1,HttpStatus.OK);
	}
	
}
