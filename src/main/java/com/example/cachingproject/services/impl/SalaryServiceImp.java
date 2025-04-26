package com.example.cachingproject.services.impl;

import com.example.cachingproject.entities.Employee;
import com.example.cachingproject.entities.SalaryAccount;
import com.example.cachingproject.repositories.SalaryRepository;
import com.example.cachingproject.services.SalaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class SalaryServiceImp implements SalaryService {

    private final SalaryRepository salaryRepository;

    @Override
    public void createAccount(Employee employee) {
        if(employee.getName().equals("MD")) throw new RuntimeException("MD is not allow");

        SalaryAccount salaryAccount = SalaryAccount.builder()
                .employee(employee)
                .balance(new BigDecimal("25000.74"))
                .build();

        salaryRepository.save(salaryAccount);
    }
}
