package ec.ecu.ups.icc.employees.service;

import ec.ecu.ups.icc.employees.entity.EmployeesEntity;
import ec.ecu.ups.icc.employees.dto.EmployeeTransferResponseDto;
import java.util.List;

public interface EmployeesService {

    EmployeesEntity create(EmployeesEntity employee);

    EmployeesEntity update(Long id, EmployeesEntity employee);

    EmployeesEntity getById(Long id);

    List<EmployeesEntity> getAll();

    List<EmployeesEntity> getByDepartmentId(Long departmentId);

    EmployeeTransferResponseDto transferEmployee(Long employeeId, Long newDepartmentId);

    void delete(Long id);
}
