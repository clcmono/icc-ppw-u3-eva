package ec.ecu.ups.icc.Company.dto;

import java.math.BigDecimal;
import java.util.List;

public class CompanyDepartmentsDto {

    private Long companyId;
    private String companyName;
    private String country;
    private int departmentCount;
    private BigDecimal totalBudget;
    private List<DepartmentWithCountDto> departments;

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getDepartmentCount() {
        return departmentCount;
    }

    public void setDepartmentCount(int departmentCount) {
        this.departmentCount = departmentCount;
    }

    public BigDecimal getTotalBudget() {
        return totalBudget;
    }

    public void setTotalBudget(BigDecimal totalBudget) {
        this.totalBudget = totalBudget;
    }

    public List<DepartmentWithCountDto> getDepartments() {
        return departments;
    }

    public void setDepartments(List<DepartmentWithCountDto> departments) {
        this.departments = departments;
    }

    public static class DepartmentWithCountDto {
        private Long id;
        private String name;
        private BigDecimal budget;
        private int employeeCount;

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

        public BigDecimal getBudget() {
            return budget;
        }

        public void setBudget(BigDecimal budget) {
            this.budget = budget;
        }

        public int getEmployeeCount() {
            return employeeCount;
        }

        public void setEmployeeCount(int employeeCount) {
            this.employeeCount = employeeCount;
        }
    }
}
