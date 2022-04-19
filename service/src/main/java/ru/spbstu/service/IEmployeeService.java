package ru.spbstu.service;


import ru.spbstu.entity.Employee;

import java.util.List;

public interface IEmployeeService {
    List<Employee> listEmployees();
    Employee getEmployee(long id);
    Employee addEmployee(Employee employee);
    void deleteEmployee(long id);
}
