package com.example.cachingproject.services;

import com.example.cachingproject.dto.SalaryDto;
import com.example.cachingproject.entities.Employee;

public interface SalaryService {
    void createAccount(Employee employee);

    SalaryDto incrementSal(Long accountId);
}
