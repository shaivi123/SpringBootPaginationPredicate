package com.demo.springboot.employeeDetails.controller;

import com.demo.springboot.employeeDetails.dto.EmployeeReqDto;
import com.demo.springboot.employeeDetails.dto.PageRequestDto;
import com.demo.springboot.employeeDetails.dto.employeeRequest;
import com.demo.springboot.employeeDetails.entity.Employee;
import com.demo.springboot.employeeDetails.repository.EmployeeRepository;
import com.demo.springboot.employeeDetails.service.EmployeeService;
import com.demo.springboot.employeeDetails.service.FiltersSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.*;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private FiltersSpecification<Employee> studentFiltersSpecification;

    @PostMapping("/saveEmployee")
    public Employee saveEmployeeDetails(@RequestBody EmployeeReqDto dto){
        return employeeService.saveEmployeeDetails(dto.getName(),dto.getAddress(),dto.getCity(),dto.getMobile(),dto.getMail());
    }


    @PostMapping("/specification/pagination")
    public Page<Employee> getStudents(@RequestBody employeeRequest requestDto) {
        Specification<Employee> searchSpecification = studentFiltersSpecification
                .getSearchSpecification(requestDto.getSearchRequestDto(), requestDto.getGlobalOperator());
        Pageable pageable = new PageRequestDto().getPageable(requestDto.getPageDto());
        return employeeRepository.findAll(searchSpecification, pageable );
    }
}































//    @GetMapping("/retrievingEmployee")
//    public List<Employee> retrievingAllEmployee(){
//        return employeeService.retrievingAllEmployee();
//    }


//    @GetMapping("/pagingAndSortingEmployee/{pageNumber}/{pageSize}")
//    public Page<Employee> employeePagination(@RequestBody String name, @PathVariable int pageNumber, @PathVariable int pageSize){
//        if (name != null && !name.isEmpty()) {
//            return employeeService.getEmployeeByNamePagination(name, pageNumber, pageSize);
//        } else {
//            return employeeService.getEmployeePagination(pageNumber, pageSize);
//        }
//    }

//    @GetMapping("/pagingAndSortingEmployee/{pageNumber}/{pageSize}")
//    public Page<Employee> employeePagination(@RequestBody String name, @PathVariable int pageNumber, @PathVariable int pageSize){
//        return employeeService.getEmployees(name, pageNumber, pageSize);
//    }

//    @GetMapping("/employees")
//    public Page<Employee> getEmployees(
//            @RequestParam(required = false) String name,
//            Pageable pageable
//    ) {
//        return employeeService.getEmployees(name, pageable);
//    }