package ec.ecu.ups.icc.employees.dto;

public class EmployeeTransferRequestDto {

    private Long newDepartmentId;

    public Long getNewDepartmentId() {
        return newDepartmentId;
    }

    public void setNewDepartmentId(Long newDepartmentId) {
        this.newDepartmentId = newDepartmentId;
    }
}
