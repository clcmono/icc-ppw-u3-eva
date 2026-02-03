package ec.ecu.ups.icc.employees.dto;

public class EmployeeTransferResponseDto {

    private Long employeeId;
    private String employeeName;
    private DepartmentSimpleDto oldDepartment;
    private DepartmentSimpleDto newDepartment;
    private String message;

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public DepartmentSimpleDto getOldDepartment() {
        return oldDepartment;
    }

    public void setOldDepartment(DepartmentSimpleDto oldDepartment) {
        this.oldDepartment = oldDepartment;
    }

    public DepartmentSimpleDto getNewDepartment() {
        return newDepartment;
    }

    public void setNewDepartment(DepartmentSimpleDto newDepartment) {
        this.newDepartment = newDepartment;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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
