package com.example.cachingproject.services.impl;

import com.example.cachingproject.dto.SalaryDto;
import com.example.cachingproject.entities.Employee;
import com.example.cachingproject.entities.SalaryAccount;
import com.example.cachingproject.repositories.SalaryRepository;
import com.example.cachingproject.services.SalaryService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
@Transactional(propagation = Propagation.REQUIRED)
public class SalaryServiceImp implements SalaryService {

    private final SalaryRepository salaryRepository;
    private final ModelMapper modelMapper;

    @Override
    public void createAccount(Employee employee) {
        if(employee.getName().equals("MD")) throw new RuntimeException("MD is not allow");

        SalaryAccount salaryAccount = SalaryAccount.builder()
                .employee(employee)
                .balance(BigDecimal.ZERO)
                .build();

        salaryRepository.save(salaryAccount);
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public SalaryDto incrementSal(Long accountId) {
        SalaryAccount salaryAccount = salaryRepository.findById(accountId).orElseThrow(
                ()->new RuntimeException("salary with id "+accountId+" not found."));

        BigDecimal prevSal = salaryAccount.getBalance();
        BigDecimal newSal = prevSal.add(BigDecimal.valueOf(1L));

        salaryAccount.setBalance(newSal);
        salaryRepository.save(salaryAccount);

        return modelMapper.map(salaryAccount,SalaryDto.class);
    }
}

/* Transactional Propagation

1. REQUIRED (default)
Behavior: If there's an existing transaction, join it. If not, create a new one.
Use case: Most common. You require a transaction, no matter if it exists or not.
Example: Saving or updating something.

2. REQUIRES_NEW
Behavior: Always creates a new transaction. If there's already one, it suspends the current one first.
Use case: When you need the method to always run independently, like logging or audit purposes.

3. MANDATORY (what you used here)
Behavior: Must run within an existing transaction. If no transaction exists, it throws an exception.
Use case: You want to force a method to always participate in a larger transaction — never alone.
Example: createAccount() must be called as a part of a bigger transaction (e.g., employee onboarding).

4. SUPPORTS
Behavior: If a transaction exists, join it. If none exists, run without a transaction.
Use case: Read-only operations. You don't require a transaction but can use it if available.

5. NOT_SUPPORTED
Behavior: Always run without a transaction. If a transaction exists, suspend it first.
Use case: Sometimes you deliberately don't want transactions, like fetching non-critical data.

6. NEVER
Behavior: Must not run in a transaction. If a transaction exists, throw an exception.
Use case: Methods that must remain completely outside transactions, maybe due to external system restrictions.

7. NESTED
Behavior: Runs within the current transaction but can create a savepoint. If inner transaction fails, only inner part rolls back, not the whole parent transaction.
Use case: Useful for partial rollbacks inside large processes.


@Transactional(isolation = Isolation.SERIALIZABLE)

Multiple transactions can still exist — it's just that the database uses heavy locks (read/write locks) to force transactions to behave serially.

In simple words:
✅ Acts as if transactions are executed one after the other.
✅ Ensures complete consistency.
⛔ Can cause slower performance (because of locking and waiting).

calling multiple times same request:- (cmd)
for /l %i in (1,1,100) do curl --location --request PUT "http://127.0.0.1:8080/employees/incrementSalary/1"

 */


