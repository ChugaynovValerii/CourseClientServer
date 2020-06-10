package ru.spbstu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import ru.spbstu.entity.Employee;
import ru.spbstu.exception.EmployeeNotFoundException;
import ru.spbstu.repository.EmployeeRepository;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService implements IEmployeeService {
    
    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }
    
    private final EmployeeRepository employeeRepository;
    
    @Override
    public List<Employee> listEmployees() {
        return (List<Employee>) employeeRepository.findAll();
    }
    
    @Override
    public Employee getEmployee(long id) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        if (optionalEmployee.isPresent()) {
            return optionalEmployee.get();
        } else {
            throw new EmployeeNotFoundException("Employee not found");
        }
    }
    
    @Override
    public Employee addEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }
    
    @Override
    public void deleteEmployee(long id) {
        try {
            employeeRepository.deleteById(id);
        } catch (EmptyResultDataAccessException exception) {
            throw new EmployeeNotFoundException("There is no employee with this ID");
        }
    }
}
