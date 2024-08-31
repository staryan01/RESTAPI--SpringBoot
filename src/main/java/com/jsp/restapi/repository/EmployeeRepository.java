package com.jsp.restapi.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.restapi.dto.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer>
{

	boolean existsByMobile(long mobile);

	List<Employee> findByName(String name);

	List<Employee> findBySalary(double salary);

	Employee findByMobile(long mobile);




}
