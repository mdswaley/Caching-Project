package com.example.cachingproject.dto;

import com.example.cachingproject.entities.Employee;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class SalaryDto {
    private Long id;
    private BigDecimal balance;
    @JsonIgnore
    private Employee employee;
}
