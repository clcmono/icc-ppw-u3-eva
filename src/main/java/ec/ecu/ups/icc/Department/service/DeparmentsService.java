package ec.ecu.ups.icc.Department.service;

import ec.ecu.ups.icc.Department.entity.DeparmentsEntity;
import ec.ecu.ups.icc.Department.dto.DepartmentWithEmployeesDto;
import java.util.List;

public interface DeparmentsService {

    DeparmentsEntity create(DeparmentsEntity department);

    DeparmentsEntity update(Long id, DeparmentsEntity department);

    DeparmentsEntity getById(Long id);

    List<DeparmentsEntity> getAll();

    List<DeparmentsEntity> getByCompanyId(Long companyId);

    DepartmentWithEmployeesDto getDepartmentWithEmployees(Long id, String sort);

    void delete(Long id);
}
