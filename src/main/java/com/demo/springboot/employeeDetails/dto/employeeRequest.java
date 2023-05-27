package com.demo.springboot.employeeDetails.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class employeeRequest {

    private List<SearchRequestDto> searchRequestDto;
    private PageRequestDto pageDto;
    private GlobalOperator globalOperator;

    public enum GlobalOperator{
        AND, OR;
    }

}
