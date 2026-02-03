package ec.ecu.ups.icc.employees.service;

import ec.ecu.ups.icc.employees.entity.EmployeesEntity;
import java.util.List;

public interface EmployeesService {

    EmployeesEntity create(EmployeesEntity employee);

    EmployeesEntity update(Long id, EmployeesEntity employee);

    EmployeesEntity getById(Long id);

    List<EmployeesEntity> getAll();

    List<EmployeesEntity> getByDepartmentId(Long departmentId);

    void delete(Long id);
}
