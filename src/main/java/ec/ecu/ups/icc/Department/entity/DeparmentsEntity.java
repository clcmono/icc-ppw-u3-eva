package ec.ecu.ups.icc.Department.entity;

import ec.ecu.ups.icc.Company.entity.Companies;
import ec.ecu.ups.icc.core.entities.BaseModel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "departments")
public class DeparmentsEntity extends BaseModel {

    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @Column(name = "budget", precision = 12, scale = 2)
    private BigDecimal budget;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "company_id")
    private Companies company;

    @Column(name = "active", length = 1, nullable = false)
    private String active;

    // ================= CONSTRUCTORS =================
    public DeparmentsEntity() {
    }

    public DeparmentsEntity(String name, BigDecimal budget, Companies company, String active) {
        this.name = name;
        this.budget = budget;
        this.company = company;
        this.active = active;
    }

    // ================= GETTERS =================
    public String getName() {
        return name;
    }

    public BigDecimal getBudget() {
        return budget;
    }

    public Companies getCompany() {
        return company;
    }

    public String getActive() {
        return active;
    }

    // ================= SETTERS =================
    public void setName(String name) {
        this.name = name;
    }

    public void setBudget(BigDecimal budget) {
        this.budget = budget;
    }

    public void setCompany(Companies company) {
        this.company = company;
    }

    public void setActive(String active) {
        this.active = active;
    }
}
