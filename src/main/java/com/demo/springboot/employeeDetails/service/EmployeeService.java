package com.demo.springboot.employeeDetails.service;

import com.demo.springboot.employeeDetails.dto.SearchRequestDto;
import com.demo.springboot.employeeDetails.entity.Employee;
import com.demo.springboot.employeeDetails.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public Employee saveEmployeeDetails(String name, String address, String city, long mobile, String mail) {
        Employee employee =new Employee();
        employee.setName(name);
        employee.setAddress(address);
        employee.setCity(city);
        employee.setMobile(mobile);
        employee.setMail(mail);
        return employeeRepository.save(employee);
    }


}
