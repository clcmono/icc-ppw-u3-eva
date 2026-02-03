package ec.ecu.ups.icc.Company.service;

import ec.ecu.ups.icc.Company.entity.Companies;
import ec.ecu.ups.icc.Company.dto.CompanyDepartmentsDto;
import java.util.List;
import ec.ecu.ups.icc.employees.dto.EmployeesResponseDto;
import java.math.BigDecimal;

public interface CompaniesService {

    Companies create(Companies companies);

    Companies update(Long id, Companies companies);

    Companies getById(Long id);

    List<Companies> getAll();

    CompanyDepartmentsDto getCompanyDepartments(Long id);

    EmployeesResponseDto getHighSalaryEmployees(Long id, BigDecimal minSalary);

    void delete(Long id);
}
