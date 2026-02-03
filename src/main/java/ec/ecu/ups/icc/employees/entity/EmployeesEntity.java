package ec.ecu.ups.icc.employees.entity;

import ec.ecu.ups.icc.Department.entity.DeparmentsEntity;
import ec.ecu.ups.icc.core.entities.BaseModel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.FetchType;
import jakarta.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "employees")
public class EmployeesEntity extends BaseModel {

    @Column(name = "first_name", length = 50, nullable = false)
    private String firstName;

    @Column(name = "last_name", length = 50, nullable = false)
    private String lastName;

    @Column(name = "email", length = 100, nullable = false)
    private String email;

    @Column(name = "salary", precision = 12, scale = 2)
    private BigDecimal salary;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "department_id")
    private DeparmentsEntity department;

    @Column(name = "active", length = 1, nullable = false)
    private String active;

    // ================= CONSTRUCTORS =================
    public EmployeesEntity() {
    }

    public EmployeesEntity(String firstName, String lastName, String email, BigDecimal salary,
            DeparmentsEntity department, String active) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.salary = salary;
        this.department = department;
        this.active = active;
    }

    // ================= GETTERS =================
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public DeparmentsEntity getDepartment() {
        return department;
    }

    public String getActive() {
        return active;
    }

    // ================= SETTERS =================
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public void setDepartment(DeparmentsEntity department) {
        this.department = department;
    }

    public void setActive(String active) {
        this.active = active;
    }
}
