package ec.ecu.ups.icc.employees.dto;

import java.math.BigDecimal;
import java.util.List;

public class EmployeesResponseDto {

    private String companyName;
    private BigDecimal minSalary;
    private int count;
    private List<EmployeeWithDepartmentDto> employees;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public BigDecimal getMinSalary() {
        return minSalary;
    }

    public void setMinSalary(BigDecimal minSalary) {
        this.minSalary = minSalary;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<EmployeeWithDepartmentDto> getEmployees() {
        return employees;
    }

    public void setEmployees(List<EmployeeWithDepartmentDto> employees) {
        this.employees = employees;
    }

    public static class EmployeeWithDepartmentDto {
        private Long id;
        private String firstName;
        private String lastName;
        private String email;
        private BigDecimal salary;
        private DepartmentSimpleDto department;

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

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public BigDecimal getSalary() {
            return salary;
        }

        public void setSalary(BigDecimal salary) {
            this.salary = salary;
        }

        public DepartmentSimpleDto getDepartment() {
            return department;
        }

        public void setDepartment(DepartmentSimpleDto department) {
            this.department = department;
        }
    }

    public static class DepartmentSimpleDto {
        private Long id;
        private String name;

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
}
