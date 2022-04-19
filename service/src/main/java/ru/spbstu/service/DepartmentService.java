package ru.spbstu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import ru.spbstu.entity.Department;
import ru.spbstu.exception.DepartmentNotFoundException;
import ru.spbstu.repository.DepartmentRepository;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService implements IDepartmentService {
    
    private final DepartmentRepository departmentRepository;
    
    @Autowired
    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }
    
    @Override
    public List<Department> listDepartments() {
        return (List<Department>) departmentRepository.findAll();
    }
    
    @Override
    public Department getDepartment(long id) {
        Optional<Department> optionalDepartment = departmentRepository.findById(id);
        if (optionalDepartment.isPresent()) {
            return optionalDepartment.get();
        } else {
            throw new DepartmentNotFoundException("Department not found");
        }
    }
    
    @Override
    public Department getDepartment(String name) {
        Optional<Department> optionalApp = Optional.ofNullable(departmentRepository.findByName(name));
        if (optionalApp.isPresent()) {
            return optionalApp.get();
        } else {
            throw new DepartmentNotFoundException("Department not found");
        }
    }
    
    @Override
    public Department addDepartments(Department department) {
        return departmentRepository.save(department);
    }
    
    @Override
    public void deleteDepartment(long id) {
        try {
            departmentRepository.deleteById(id);
        } catch (EmptyResultDataAccessException exception) {
            throw new DepartmentNotFoundException("There is no department with this ID");
        }
    }
}
