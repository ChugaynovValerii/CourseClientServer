package ru.spbstu.repository;

import org.springframework.data.repository.CrudRepository;
import ru.spbstu.entity.Department;

public interface DepartmentRepository extends CrudRepository<Department, Long> {
    Department findByName(String name);
}
