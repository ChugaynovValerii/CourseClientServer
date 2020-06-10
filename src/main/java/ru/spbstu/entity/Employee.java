package ru.spbstu.entity;

import javax.persistence.*;

@Entity
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(columnDefinition = "VARCHAR2(20) NOT NULL")
    private String firstName;
    
    @Column(columnDefinition = "VARCHAR2(20) NOT NULL")
    private String lastName;
    
    @Column(columnDefinition = "VARCHAR2(20) NOT NULL")
    private String fatherName;
    
    @Column(columnDefinition = "VARCHAR2(50) NOT NULL")
    private String position;
    
    @Column(columnDefinition = "NUMBER(18,2) NOT NULL")
    private Float salary;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinTable(name = "departments_employees",
            joinColumns = @JoinColumn(name = "employees_fk"),
            inverseJoinColumns = @JoinColumn(name = "departments_fk"))
    private Department department;

    public Employee() {
    }

    public Employee(String firstName, String lastName, String fatherName, String position, Float salary) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.fatherName = fatherName;
        this.position = position;
        this.salary = salary;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public String getFatherNameName() {
        return fatherName;
    }
    
    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }
    
    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Float getSalary() {
        return salary;
    }

    public void setSalary(Float salary) {
        this.salary = salary;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
