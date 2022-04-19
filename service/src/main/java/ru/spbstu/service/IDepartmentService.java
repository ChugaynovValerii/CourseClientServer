package ru.spbstu.service;


import ru.spbstu.entity.Department;

import java.util.List;

public interface IDepartmentService {
    List<Department> listDepartments();
    Department getDepartment(long id);
    Department getDepartment(String name);
    Department addDepartments(Department department);
    void deleteDepartment(long id);
}
