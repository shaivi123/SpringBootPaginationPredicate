package com.demo.springboot.employeeDetails.service;

import com.demo.springboot.employeeDetails.dto.SearchRequestDto;
import com.demo.springboot.employeeDetails.dto.employeeRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Service
public class FiltersSpecification<T> {

    public Specification<T> getSearchSpecification(SearchRequestDto searchRequestDto) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(
                        criteriaBuilder.lower(root.get(searchRequestDto.getColumn())),
                        "%" + searchRequestDto.getValue().toLowerCase() + "%"
                );
    }
    //we pass list in argument because we got the list of searchRequestDto for multiple filter
    public Specification<T> getSearchSpecification(List<SearchRequestDto> searchRequestDtos, employeeRequest.GlobalOperator globalOperator) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            for (SearchRequestDto requestDto : searchRequestDtos) {
                String[] columns = requestDto.getColumn().split(",");
                String[] values = requestDto.getValue().split(",");

                if (columns.length != values.length) {
                    throw new IllegalArgumentException("Number of columns and values does not match");
                }

                List<Predicate> columnPredicates = new ArrayList<>();

                for (int i = 0; i < columns.length; i++) {
                    String column = columns[i].trim();
                    String value = values[i].trim();

                    Predicate columnPredicate = criteriaBuilder.and(
                            criteriaBuilder.equal(root.get(column), value)
                    );

                    columnPredicates.add(columnPredicate);
                }

                Predicate finalPredicate = criteriaBuilder.and(columnPredicates.toArray(new Predicate[0]));
                predicates.add(finalPredicate);
            }

            if (predicates.isEmpty()) {
                // No search criteria provided, return appropriate message or handle the scenario
                // For example, throw an exception or return an empty result
                throw new IllegalArgumentException("No search criteria provided");
            }

            if (globalOperator.equals(employeeRequest.GlobalOperator.AND)) {
                return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
            } else {
                return criteriaBuilder.or(predicates.toArray(new Predicate[0]));
            }
        };
    }

}
