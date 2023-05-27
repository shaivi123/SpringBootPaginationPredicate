package com.demo.springboot.employeeDetails.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeReqDto {

    private String name;
    private String address;
    private String city;
    private long mobile;
    private String mail;
}
