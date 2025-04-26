package com.example.cachingproject.repositories;

import com.example.cachingproject.entities.SalaryAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface SalaryRepository extends JpaRepository<SalaryAccount, Long> {
}
