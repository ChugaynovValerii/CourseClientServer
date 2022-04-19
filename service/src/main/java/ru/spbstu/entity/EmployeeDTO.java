package ru.spbstu.entity;

public class EmployeeDTO {
    
    private Long id;
    
    private String firstName;
    
    private String lastName;
    
    private String fatherName;
    
    private String position;
    
    private Float salary;
    
    private String department;
    
    public EmployeeDTO() {
    }
    
    public EmployeeDTO(Long id, String firstName, String lastName, String fatherName, String position, Float salary, String department) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.fatherName = fatherName;
        this.position = position;
        this.salary = salary;
        this.department = department;
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
    
    public String getFatherName() {
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
    
    public String getDepartment() {
        return department;
    }
    
    public void setDepartment(String department) {
        this.department = department;
    }
}
