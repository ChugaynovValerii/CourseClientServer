package ru.spbstu.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import java.sql.Date;

@Entity
public class Project {
    @Id
    @SequenceGenerator(
            name = "project_sequence",
            sequenceName = "project_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "project_sequence")
    private Long id;
    
    @Column(columnDefinition = "VARCHAR(200)")
    private String name;
    
    @Column(columnDefinition = "NUMERIC(18,2)")
    private Float cost;
    
    @Column
    private Date dateBeg;
    
    @Column
    private Date dateEnd;
    
    @Column
    private Date dateEndReal;
    
    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;
    
    public Project() {
    }
    
    public Project(String name, Float cost, Date dateBeg, Date dateEnd, Date dateEndReal, Department department) {
        this.name = name;
        this.cost = cost;
        this.dateBeg = dateBeg;
        this.dateEnd = dateEnd;
        this.dateEndReal = dateEndReal;
        this.department = department;
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
    
    public Float getCost() {
        return cost;
    }
    
    public void setCost(Float cost) {
        this.cost = cost;
    }
    
    public Date getDateBeg() {
        return dateBeg;
    }
    
    public void setDateBeg(Date dateBeg) {
        this.dateBeg = dateBeg;
    }
    
    public Date getDateEnd() {
        return dateEnd;
    }
    
    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }
    
    public Date getDateEndReal() {
        return dateEndReal;
    }
    
    public void setDateEndReal(Date dateEndReal) {
        this.dateEndReal = dateEndReal;
    }
    
    public Department getDepartment() {
        return department;
    }
    
    public void setDepartment(Department department) {
        this.department = department;
    }
}
