package com.example.cachingproject.repositories;

import com.example.cachingproject.entities.SalaryAccount;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import java.util.Optional;

public interface SalaryRepository extends JpaRepository<SalaryAccount, Long> {
    @Override
//    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<SalaryAccount> findById(Long id);
}
