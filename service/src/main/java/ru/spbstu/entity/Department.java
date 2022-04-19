package ru.spbstu.entity;

import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import java.util.List;

@Entity
public class Department {
    @Id
    @SequenceGenerator(
            name = "department_sequence",
            sequenceName = "department_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "department_sequence")
    private Long id;
    
    @Column(columnDefinition = "VARCHAR(20)")
    private String name;
    
    @OneToMany(mappedBy = "department")
    @ToString.Exclude
    private List<Employee> employees;
    
    @OneToMany(mappedBy = "department")
    @ToString.Exclude
    private List<Project> projects;
    
    public Department() {
    }
    
    public Department(String name) {
        this.name = name;
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
}
