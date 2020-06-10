package ru.spbstu.repository;

import org.springframework.data.repository.CrudRepository;
import ru.spbstu.entity.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {
}