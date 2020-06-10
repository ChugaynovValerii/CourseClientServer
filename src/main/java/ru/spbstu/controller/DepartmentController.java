package ru.spbstu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.spbstu.entity.Department;
import ru.spbstu.exception.DepartmentNotFoundException;
import ru.spbstu.service.IDepartmentService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/departments")
public class DepartmentController {
    private final IDepartmentService departmentService;
    
    @Autowired
    public DepartmentController(IDepartmentService departmentService) {
        this.departmentService = departmentService;
    }
    
    @GetMapping("/list")
    public ResponseEntity<List<Department>> getAllDepartments() {
        List<Department> list = new ArrayList<>();
        try {
            list = departmentService.listDepartments();
        } catch (Exception e) {
            return new ResponseEntity<>(list, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    
    @GetMapping("/list/{id}")
    public ResponseEntity<Department> getDepartment(@PathVariable("id") long id) {
        try {
            return new ResponseEntity<>(departmentService.getDepartment(id), HttpStatus.OK);
        } catch (DepartmentNotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Department not found");
        }
    }
    
    @GetMapping("/{name}")
    public ResponseEntity<Department> getDepartment(@PathVariable("name") String name) {
        try {
            return new ResponseEntity<>(departmentService.getDepartment(name), HttpStatus.OK);
        } catch (DepartmentNotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Department not found");
        }
    }
    
    @PostMapping(value = "/addDepartment", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Department> addDepartment(@RequestBody Department department) {
        Department tempDepartment = new Department();
        try {
            tempDepartment = departmentService.addDepartments(department);
        } catch (Exception e) {
            return new ResponseEntity<>(tempDepartment, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(tempDepartment, HttpStatus.OK);
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Department> deleteDepartment(@PathVariable("id") long id) {
        try {
            departmentService.deleteDepartment(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (DepartmentNotFoundException exception) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}