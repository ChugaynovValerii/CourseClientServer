package ru.spbstu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.spbstu.entity.Department;
import ru.spbstu.entity.Employee;
import ru.spbstu.entity.EmployeeDTO;
import ru.spbstu.exception.EmployeeNotFoundException;
import ru.spbstu.service.IDepartmentService;
import ru.spbstu.service.IEmployeeService;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    private final IEmployeeService employeeService;
    private final IDepartmentService departmentService;
    
    @Autowired
    public EmployeeController(IEmployeeService employeeService, IDepartmentService departmentService) {
        this.employeeService = employeeService;
        this.departmentService = departmentService;
    }
    
    @GetMapping("/list")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> list = employeeService.listEmployees();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    
    @GetMapping("/list/{id}")
    public ResponseEntity<Employee> getEmployee(@PathVariable("id") long id) {
        try {
            return new ResponseEntity<>(employeeService.getEmployee(id), HttpStatus.OK);
        } catch (EmployeeNotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found");
        }
    }
    
    @PostMapping(value = "/addEmployee", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Employee> addEmployee(@RequestBody EmployeeDTO employeeDto) {
        Employee employee = new Employee(employeeDto.getFirstName(), employeeDto.getLastName(), employeeDto.getFatherName(),
                employeeDto.getPosition(), employeeDto.getSalary());
        
        Department department = departmentService.getDepartment(employeeDto.getDepartment());
        employee.setDepartment(department);
        return new ResponseEntity<>(employeeService.addEmployee(employee), HttpStatus.OK);
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Employee> deleteEmployee(@PathVariable("id") long id) {
        try {
            employeeService.deleteEmployee(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (EmployeeNotFoundException exception) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
