package com.example.cachingproject.services.impl;

import com.example.cachingproject.dto.EmployeeDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
class EmployeeServiceImplTest {

    @Autowired
    private EmployeeServiceImpl employeeService;

    @Test
    public void getEmpByIdTestIfPresent(){
        EmployeeDto employeeDto = employeeService.getEmployeeById(1L);
        log.info(employeeDto.toString());
    }


}